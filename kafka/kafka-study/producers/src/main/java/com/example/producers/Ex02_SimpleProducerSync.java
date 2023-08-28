package com.example.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.example.producers.config.KafkaConfig.SERVER_PORT;

public class Ex02_SimpleProducerSync {
    public static final Logger LOGGER = LoggerFactory.getLogger(Ex02_SimpleProducerSync.class);
    public static void main(String[] args) {
        String topicName = "simple-topic";

        //kafkaProducer configuration settings
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_PORT);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // kafkaProducer instance
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // Producer record object created
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, "hello world3");

        // KafkaProducer message send
        try {
            RecordMetadata recordMetadata = kafkaProducer.send(producerRecord).get();
            LOGGER.info("\n ###### record metadata received ###### \n" +
                    "partition: " + recordMetadata.partition() + "\n" +
                    "offset: " + recordMetadata.offset() + "\n" +
                    "timestamp: " + recordMetadata.timestamp()
            );

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }

        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
