/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueueDemo.
 *
 * @author Zhou Fan, 2019年12月30日
 * @version OPRA.1.0.0
 */

public class SynchronousQueueDemo {

    /**
     * 1、队列
     * 
     * 
     * 
     * 2、阻塞队列
     *   2.1、阻塞队列有没有好的一面
     *   2.2、不得不阻塞，你如何管理
     * 
     * 
     * 
     * 
     * SynchronousQueue同步队列
     * @param args
     */
    public static void main(String[] args) {
        BlockingQueue<String> queue=new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"    put 1");
                queue.put("1");
                
                System.out.println(Thread.currentThread().getName()+"    put 2");
                queue.put("2");
                
                System.out.println(Thread.currentThread().getName()+"    put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"    get 1");
                queue.take();

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"    get 2");
                queue.take();
               
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"    get 3");
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}