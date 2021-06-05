package com.zhoufan.kafka.serializer;

import com.zhoufan.kafka.constant.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

/**
 * 自定义 对象序列化 反序列化
 */
public class KafkaProducerService {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserSerializer.class);

        //2.创建生产者
        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(props);

        //3.封账消息队列
        for (Integer i = 0; i < 10; i++) {
            ProducerRecord<String, User> record = new ProducerRecord<>("topic05", "key" + i, new User(i, "user" + i, new Date()));
            producer.send(record);
        }

        producer.close();
    }
}
