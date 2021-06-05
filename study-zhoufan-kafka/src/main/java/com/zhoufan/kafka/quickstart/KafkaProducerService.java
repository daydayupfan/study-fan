/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.kafka.quickstart;

import com.zhoufan.kafka.constant.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * KafkaProducerService 生产者服务.
 *
 * @author Zhou Fan, 2021年04月04日
 * @version OPRA. v.1.0.0
 */

public class KafkaProducerService {

    public static void main(String[] args) throws InterruptedException {
        //1.创建链接参数
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //2.创建生产者
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 30; i++) {
            Thread.sleep(100);
            //存在key 负载均衡算法是hash 不存在的是组内均分
            ProducerRecord<String, String> record = new ProducerRecord<>("topic01", "v" + i);
            producer.send(record);
        }
        producer.close();
    }
}