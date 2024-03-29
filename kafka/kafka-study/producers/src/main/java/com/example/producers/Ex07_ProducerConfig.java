package com.example.producers;

import com.github.javafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.TransactionManager;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static com.example.producers.config.KafkaConfig.SERVER_PORT;

public class Ex07_ProducerConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(Ex07_ProducerConfig.class);

    public static void sendPizzaMessage(KafkaProducer<String, String> kafkaProducer,
                                        String topicName, int iterCount, int interIntervalMillis,
                                        int intervalMillis, int intervalCount, boolean sync) {
        PizzaMessage pizzaMessage = new PizzaMessage();
        int iterSeq = 0;
        long seed = 2022;
        Random random = new Random(seed);
        Faker faker = Faker.instance(random);

        while (iterSeq != iterCount) {
            Map<String, String> pMessage = pizzaMessage.produce_msg(faker, random, iterSeq);
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>(topicName, pMessage.get("key"), pMessage.get("message"));
            sendMessage(kafkaProducer, producerRecord, pMessage, sync);

            if (intervalCount > 0 && iterSeq % intervalCount == 0) {
                try {
                    LOGGER.info("###### IntervalCount: {} intervalMillis: {} ######" , intervalCount, intervalMillis);
                    Thread.sleep(intervalMillis);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }
            }

            if (interIntervalMillis > 0) {
                try {
                    LOGGER.info("###### interIntervalMillis: {} ######" , interIntervalMillis);
                    Thread.sleep(interIntervalMillis);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }
            }

            iterSeq++;
        }
    }

    public static void sendMessage(KafkaProducer<String, String> kafkaProducer,
                                   ProducerRecord<String, String> producerRecord,
                                   Map<String, String> pMessage, boolean sync) {
        if (!sync) {
            kafkaProducer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    LOGGER.info("async message: " + pMessage.get("key") +
                            " partition: " + metadata.partition() +
                            " offset: " + metadata.offset()
                    );
                } else {
                    LOGGER.error("exception error from broker " + exception.getMessage());
                }
            });
        } else {
            try {
                RecordMetadata metadata = kafkaProducer.send(producerRecord).get();
                LOGGER.info("sync message: " + pMessage.get("key") +
                        " partition: " + metadata.partition() +
                        " offset: " + metadata.offset()
                );

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                kafkaProducer.close();
            }
        }
    }

    public static void main(String[] args) {
        String topicName = "pizza-topic";

        //kafkaProducer configuration settings
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_PORT);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        /**
         *      acks    speed   description
         *      0       상       프로듀서는 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않습니다.
         *      1       중       프로듀서는 자신이 보낸 메시지에 대해 카프카의 leader가 메시지를 받았는지 기다립니다. follower들은 확인하지 않습니다.
         *                      (leader가 확인응답을 보내고, follower에게 복제가 되기 전에 leader가 fail되면, 해당 메시지는 손실될 수 있습니다.)
         *      all(-1) 하       프로듀서는 자신이 보낸 메시지에 대해 카프카의 leader와 follower까지 받았는지 기다립니다.
         *                       최소 하나의 복제본까지 처리된 것을 확인하므로 메시지가 손실될 확률은 거의 없습니다.
         */
        properties.put(ProducerConfig.ACKS_CONFIG, "0"); // default -1

        // batch size 한 배치당 가질 수 있는 크기
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 메모리 accumulator 가 가질 수 있는 크기
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 커넥션마다 배치사이즈를 보낼 수 있는 최대 갯수
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        // default 0, 얼만큼 기다렸다가 매치를 갔다 올건지.
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 20);
        // 요청 최대 크기
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1048576);

        properties.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 120000);

        /*
            enable.idempotence를 true로 설정하여 멱등성을 보장하기 위해서는 아래의 설정도 추가적으로 필요하다.
            max.in.flight.requests.per.connection를 5보다 작거나 같은 값으로 설정
            retries을 0보다 큰 값으로 설정
            acks를 all로 설정

        만약 다른 설정 없이 enable.idempotence를 true로 설정하면 아래와 같은 로직을 타며 타른 값들을 설정한다.
        if (idempotenceEnabled) {
            String transactionalId = config.getString(ProducerConfig.TRANSACTIONAL_ID_CONFIG);
            int transactionTimeoutMs = config.getInt(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG);
            long retryBackoffMs = config.getLong(ProducerConfig.RETRY_BACKOFF_MS_CONFIG);
            transactionManager = new TransactionManager(logContext, transactionalId, transactionTimeoutMs, retryBackoffMs);
            if (transactionManager.isTransactional())
                log.info("Instantiated a transactional producer.");
            else
                log.info("Instantiated an idempotent producer.");
        }
        */
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        /**
         * 한 번에 몇 개의 요청(Request)을 전송할 것인가를 결정한다.
         * 이 프로퍼티가 1로 설정되어 있으면 프로듀서는 한 번에 하나의 요청을 전송하고 응답을 받은 이후 다음 요청을 전송한다.
         * 이 프로퍼티가 2 이상으로 설정되어 있으면 설정된 만큼 요청을 전송하고 응답을 기다린다.
         * "retries" 프로퍼티와 함께 사용할 때 메시지 전송 순서가 뒤바뀔 수 있는 문제가 있다.
         */
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 6);

        // kafkaProducer instance
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);


        sendPizzaMessage(kafkaProducer, topicName, -1, 10, 100, 100, false);


        // KafkaProducer message send

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
