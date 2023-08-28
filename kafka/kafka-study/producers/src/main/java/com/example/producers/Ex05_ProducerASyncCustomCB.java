package com.example.producers;

import com.example.producers.callback.CustomCallback;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static com.example.producers.config.KafkaConfig.SERVER_PORT;

public class Ex05_ProducerASyncCustomCB {
    public static final Logger LOGGER = LoggerFactory.getLogger(Ex05_ProducerASyncCustomCB.class);
    public static void main(String[] args) {
        String topicName = "multipart-topic";

        //kafkaProducer configuration settings
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_PORT);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // kafkaProducer instance
        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties);


        for (int i = 0; i < 20; i++) {
            // Producer record object created
            ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>(topicName, i,"hello world" + i);
            LOGGER.info("seq:{}",i);
            Callback callback = new CustomCallback(i);
            kafkaProducer.send(producerRecord, callback);
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
