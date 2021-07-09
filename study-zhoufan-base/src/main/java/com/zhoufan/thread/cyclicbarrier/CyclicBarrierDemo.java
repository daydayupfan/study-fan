/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierDemo.
 *
 * @author Zhou Fan, 2019年12月30日
 * @version OPRA.1.0.0
 */

public class CyclicBarrierDemo {
    /**
     * 集齐七颗龙珠可实现愿望 人到齐再开会  与countDownLatch（x->0）相反（0->x）
     */

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("出来吧 神龙  实现我的一切愿望！");
        });

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println("收集到第" + Thread.currentThread().getName() + "颗龙珠！");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, i + "").start();
        }
    }
}