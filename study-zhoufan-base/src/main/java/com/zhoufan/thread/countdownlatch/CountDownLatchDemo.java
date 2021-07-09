/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatchDemo.
 *
 * @author Zhou Fan, 2019年12月30日
 * @version OPRA.1.0.0
 */

public class CountDownLatchDemo {

    public static void main(String[] args) {
        countDownLatchDemoT();

    }

    protected static void countDownLatchDemoT() {
        /**
         * 可以保证子线程完成后再执行主线程
         *
         * 秦国统一六国  一个一个干掉 干掉6个才统一全国
         */
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 已走出教室");
                countDownLatch.countDown();
            }, i + "").start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("可以关门了");
    }
}