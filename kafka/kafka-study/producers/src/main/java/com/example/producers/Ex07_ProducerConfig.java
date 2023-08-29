package com.example.producers;

import com.github.javafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
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

        //ACKS_CONFIG 기본은 all (-1)
        properties.put(ProducerConfig.ACKS_CONFIG, "0");

        // kafkaProducer instance
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);


        sendPizzaMessage(kafkaProducer,topicName,-1, 10, 100,100,false);


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
