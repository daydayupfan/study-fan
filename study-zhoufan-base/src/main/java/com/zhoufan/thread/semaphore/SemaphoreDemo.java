/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * SemaphoreDemo 信号量.
 *
 * @author Zhou Fan, 2019年12月30日
 * @version OPRA.1.0.0
 */

public class SemaphoreDemo {

    /**
     * 吃火锅 等餐位一样的逻辑
     */

    public static void main(String[] args) {
        //总共五桌
        Semaphore semaphore = new Semaphore(5);
        //10波顾客
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                try {
                    semaphore.acquire();
                    System.out.println("顾客" + name + "   开始用餐....");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("顾客" + name + "   用餐3小时");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, i + "").start();
        }

    }
}