/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.kafka.dml;

import com.zhoufan.kafka.constant.KafkaConfig;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * KafkaDmlService Kafka topic基本操作 .
 *
 * @author Zhou Fan, 2021年04月04日
 * @version OPRA. v.1.0.0
 */

public class KafkaDmlService {

    /**
     * 单机环境
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] topics = {"topic01", "topic02", "topic03", "topic04", "topic05", "topic06"};
        KafkaAdminClient adminClient = createKafkaAdminClient();
        short replicationFactor = 1;
//        createTopic(adminClient, "topic03", replicationFactor);
//        queryTopics(adminClient);
//        getDescribeTopics(adminClient, "topic03");
        deleteTopic(adminClient, "topic03");
        adminClient.close();
    }


    /**
     * 创建kafka客户端
     *
     * @return KafkaAdminClient
     */
    private static KafkaAdminClient createKafkaAdminClient() {
        //配置连接参数
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.getBootstrapServer());
        //创建kafka客户端
        return (KafkaAdminClient) KafkaAdminClient.create(props);
    }

    /**
     * 查询topic
     *
     * @param adminClient adminClient
     */
    public static void queryTopics(KafkaAdminClient adminClient)
            throws ExecutionException, InterruptedException {
        ListTopicsResult topicsResult = adminClient.listTopics();
        KafkaFuture<Set<String>> nameFutures = topicsResult.names();
        System.out.println("查询所有topic");
        for (String name : nameFutures.get()) {
            System.out.println(name);
        }
    }

    /**
     * topic详细信息
     *
     * @param topicName   topicName
     * @param adminClient adminClient
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    public static void getDescribeTopics(KafkaAdminClient adminClient, String... topicName)
            throws ExecutionException, InterruptedException {
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicName));
        Map<String, TopicDescription> map = result.all().get();
        System.out.println("topic详细信息");
        map.forEach((k, v) -> System.out.println(k + "   " + v));
    }

    /**
     * 创建topic
     *
     * @param adminClient       adminClient
     * @param topicName         topicName
     * @param replicationFactor replicationFactor
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void createTopic(KafkaAdminClient adminClient, String topicName, short replicationFactor)
            throws ExecutionException, InterruptedException {
        CreateTopicsResult topics = adminClient.createTopics(getTopicList(topicName, replicationFactor));
        //.all().get() 同步操作 会及时更新topic 保证topic列表拿到的是最新的数据
        topics.all().get();
    }

    /**
     * 删除topic
     *
     * @param adminClient adminClient
     * @param topicName   topicName
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    public static void deleteTopic(KafkaAdminClient adminClient, String... topicName)
            throws ExecutionException, InterruptedException {
        DeleteTopicsResult topics = adminClient.deleteTopics(Arrays.asList(topicName));
        //.all().get() 同步操作 会及时更新topic 保证topic列表拿到的是最新的数据
        topics.all().get();
    }

    /**
     * 获取topic集合实例
     *
     * @param topicName         topicName
     * @param replicationFactor replicationFactor
     * @return List<NewTopic>
     */
    private static List<NewTopic> getTopicList(String topicName, short replicationFactor) {
        ArrayList<NewTopic> objects = new ArrayList<>();
        objects.add(new NewTopic(topicName, 3, replicationFactor));
        return objects;
    }
}