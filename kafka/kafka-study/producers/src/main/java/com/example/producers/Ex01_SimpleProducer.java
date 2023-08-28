package com.example.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Ex01_SimpleProducer {
    public static void main(String[] args) {
        String topicName = "simple-topic";

        //kafkaProducer configuration settings
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.101:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // kafkaProducer instance
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // Producer record object created
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, "hello world3");

        // KafkaProducer message send
        kafkaProducer.send(producerRecord);

        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
