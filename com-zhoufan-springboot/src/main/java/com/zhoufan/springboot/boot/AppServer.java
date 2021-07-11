/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.springboot.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AppServer 启动类.
 *
 * @author Zhou Fan, 2021年07月09日
 * @version OPRA. v.1.0.0
 */

@SpringBootApplication
public class AppServer {

    public static void main(String[] args) {
        SpringApplication.run(AppServer.class);
    }



    public static void springBeanLifeCyc(){
        AbstractXmlApplicationContext context = new ClassPathXmlApplicationContext("");
        Object bean = context.getBean("");
    }
}