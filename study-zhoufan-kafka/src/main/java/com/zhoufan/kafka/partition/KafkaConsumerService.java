package com.zhoufan.kafka.partition;

import com.zhoufan.kafka.constant.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 消费者订阅方式  subscribe
 */
public class KafkaConsumerService {
    public static void main(String[] args) {
        //1.创建Kafka链接参数
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getBootstrapServer());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //subscrribe 订阅方式是通过组管理进行的 负载均衡
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g2");

        //2.创建Topic消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //3.订阅topic开头的消息队列
        consumer.subscribe(Arrays.asList("topic04"));
        
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            Iterator<ConsumerRecord<String, String>> recordIterator = consumerRecords.iterator();
            while (recordIterator.hasNext()) {
                ConsumerRecord<String, String> record = recordIterator.next();
                String key = record.key();
                String value = record.value();
                long offset = record.offset();
                int partition = record.partition();
                String topic = record.topic();
                System.out.println("key:" + key + ",value:" + value + ",partition:"
                        + partition + ",offset:" + offset + ",topic:" + topic);
            }
        }
    }
}
