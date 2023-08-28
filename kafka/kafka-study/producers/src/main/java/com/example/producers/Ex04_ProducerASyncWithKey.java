package com.example.producers;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Ex04_ProducerASyncWithKey {
    public static final Logger LOGGER = LoggerFactory.getLogger(Ex04_ProducerASyncWithKey.class);
    public static void main(String[] args) {
        String topicName = "multipart-topic";

        //kafkaProducer configuration settings
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.101:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // kafkaProducer instance
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);


        for (int i = 0; i < 20; i++) {
            // Producer record object created
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, String.valueOf(i),"hello world" + i);
            LOGGER.info("seq:{}",i);
            kafkaProducer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    LOGGER.info("\n ###### record metadata received ###### \n" +
                            "partition: " + metadata.partition() + "\n" +
                            "offset: " + metadata.offset() + "\n" +
                            "timestamp: " + metadata.timestamp()
                    );
                } else {
                    LOGGER.error("exception error from broker " + exception.getMessage());
                }
            });
        }


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
