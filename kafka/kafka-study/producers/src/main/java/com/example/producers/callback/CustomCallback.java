package com.example.producers.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCallback implements Callback {
    public static final Logger log = LoggerFactory.getLogger("customCallback");
    private int seq;

    public CustomCallback(int seq) {
        this.seq = seq;
    }

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            log.info("seq:{} partition:{} offset:{}", this.seq, metadata.partition(), metadata.offset());
        } else {
            log.error("exception error from broker " + exception.getMessage());
        }
    }
}
