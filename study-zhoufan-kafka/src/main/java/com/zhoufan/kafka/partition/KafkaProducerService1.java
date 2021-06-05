/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.kafka.partition;

import com.zhoufan.kafka.constant.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * KafkaProducerService 生产者服务 .
 * 默认分区策略：
 * 如果在记录中指定了分区，请使用它
 * 如果未指定任何分区但存在key，则根据键的哈希值选择一个分区
 * 如果不存在分区或key，则以循环方式选择一个分区
 *
 * @author Zhou Fan, 2021年04月04日
 * @version OPRA. v.1.0.0
 */

public class KafkaProducerService1 {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, UserDefinePartitioner.class);

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            ProducerRecord<String, String> record = new ProducerRecord<>("topic04", "v" + i);
//            ProducerRecord<String, String> record = new ProducerRecord<>("topic04", 0,"k" + i, "v" + i);
            producer.send(record);
        }
        producer.close();
    }
}