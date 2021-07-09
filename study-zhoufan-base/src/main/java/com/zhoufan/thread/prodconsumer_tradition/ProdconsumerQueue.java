/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.prodconsumer_tradition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者队列 知识点整合ProdconsumerQueue.
 * 控制生产+消费
 * volatile/CAS/atomicxxx/阻塞队列/线程交互/原子引用
 *
 * @author Zhou Fan, 2019年12月31日
 * @version OPRA.1.0.0
 */

public class ProdconsumerQueue {

    public static void main(String[] args) {
        MySource source = new MySource(new ArrayBlockingQueue<>(3));

        try {
            new Thread(() -> {
                source.produce();
            }, "p").start();


            new Thread(() -> {
                source.consumer();
            }, "s").start();
            //消费20s
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {

        }
        source.stop();
    }
}


class MySource {
    private AtomicInteger num = new AtomicInteger();

    private volatile boolean FLAG = true;//默认开启 生产+消费

    private BlockingQueue<String> queue;

    public MySource(BlockingQueue<String> queue) {
        this.queue = queue;
        System.out.println("传入队列类型为[" + queue.getClass().getName() + "]");
    }


    public void produce() {
        String data = null;
        boolean result = false;
        while (FLAG) {
            data = num.incrementAndGet() + "";
            try {
                result = queue.offer(data, 2, TimeUnit.SECONDS);
                if (result) {
                    System.out.println(Thread.currentThread().getName() + "  生产数据成功 数据=[" + data + "]");
                } else {
                    System.out.println(Thread.currentThread().getName() + "  生产数据失败");
                }
                //每秒生产一次
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("停止数据生产 FLAG=false");
    }

    public void consumer() {
        String data = null;
        while (FLAG) {
            try {
                data = queue.poll(2, TimeUnit.SECONDS);
                if (null == data || "".equals(data)) {
                    System.out.println(Thread.currentThread().getName() + "  已超过2秒没有数据消费");
                    FLAG = false;
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "  消费数据成功 数据=[" + data + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop() {
        FLAG = false;
    }
}