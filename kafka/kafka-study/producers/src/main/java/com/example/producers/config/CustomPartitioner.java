package com.example.producers.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.internals.StickyPartitionCache;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {
    private final StickyPartitionCache stickyPartitionCache = new StickyPartitionCache();
    public static final Logger LOGGER = LoggerFactory.getLogger(CustomPartitioner.class);
    private String specialKey;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartitions = partitionInfos.size();
        int numSpecialPartitions = (int) (numPartitions * 0.5);
        int partitionIndex = 0;

        if (keyBytes == null) {
            return stickyPartitionCache.partition(topic, cluster);
        }

        if (specialKey.equals(key)) {
            partitionIndex = Utils.toPositive(Utils.murmur2(valueBytes)) % numSpecialPartitions;
        } else {
            partitionIndex = Utils.toPositive(Utils.murmur2(keyBytes)) % (numPartitions - numSpecialPartitions) + numSpecialPartitions;
        }
        LOGGER.info("key:{} is sent to partition:{}", key.toString(), partitionIndex);
        return partitionIndex;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        specialKey = configs.get("custom.special.key").toString();
    }
}
