/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.kafka.constant;

/**
 * KafkaConfig.
 *
 * @author Zhou Fan, 2021年04月04日
 * @version OPRA. v.1.0.0
 */

public class KafkaConfig {

    public static final String BOOTSTRAP_SERVER = "centos1:9092,centos2:9092,centos3:9092";
    public static final String BOOTSTRAP_SERVER_SINGLE = "centos4:9092";

    public static String getBootstrapServer() {
        return BOOTSTRAP_SERVER_SINGLE;
    }
}