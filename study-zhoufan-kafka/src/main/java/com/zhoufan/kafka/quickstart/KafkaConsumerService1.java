package com.zhoufan.kafka.quickstart;

import com.zhoufan.kafka.constant.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 自定义分区 指定分区 指定偏移位置开始进行消费 assign
 */
public class KafkaConsumerService1 {
    public static void main(String[] args) {
        //1.创建Kafka链接参数
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getBootstrapServer());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        TopicPartition t0 = new TopicPartition("topic03", 0);
        //3.自定义分区消息队列
        consumer.assign(Arrays.asList(t0));
        //从指定位置指定分区开始消费
        consumer.seek(t0, 0);

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
