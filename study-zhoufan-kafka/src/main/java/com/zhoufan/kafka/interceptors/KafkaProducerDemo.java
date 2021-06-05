package com.zhoufan.kafka.interceptors;

import com.zhoufan.kafka.constant.KafkaConfig;
import com.zhoufan.kafka.serializer.User;
import com.zhoufan.kafka.serializer.UserSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

public class KafkaProducerDemo {
    public static void main(String[] args) {
        //1.创建链接参数
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getBootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserSerializer.class.getName());
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, UserDefineProducerInterceptor.class.getName());

        //2.创建生产者
        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(props);

        //3.封账消息队列
        for (Integer i = 0; i < 10; i++) {
            ProducerRecord<String, User> record = new ProducerRecord<>("topic06", "key" + i,
                    new User(i, "zhoufan" + i, new Date()));
            producer.send(record);
        }

        producer.close();
    }
}
