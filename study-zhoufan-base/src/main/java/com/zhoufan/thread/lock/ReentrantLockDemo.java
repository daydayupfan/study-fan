/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {

    Lock lock = new ReentrantLock();

    public synchronized void sendMSG() {
        System.out.println(Thread.currentThread().getName() + "  sendMSG");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "  sendEmail");
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "  get");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "  set");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        get();
    }
}

/**
 * ReentrantLockDemo.
 *
 * @author Zhou Fan, 2019年12月19日
 * @version OPRA.1.0.0
 */

public class ReentrantLockDemo {


    public static void main(String[] args) throws InterruptedException {
        /**
         * 证明synchronized是重入锁(迭代锁)
         * thread1  sendMSG
         * thread1  sendEmail
         * thread2  sendMSG
         * thread2  sendEmail
         * 证明ReentrantLock是重入锁(迭代锁)
         * thread3  get
         * thread3  set
         * thread4  get
         * thread4  set
         *
         * lock unlock是一一对应的  不管有多少对 都不会报错编译不过
         * 但是不一一对应 会出现程序卡死（缺少unlock）的现象 原因在于没有释放锁 
         * 或者报错（缺少lock）
         */
        Phone phone = new Phone();
        System.out.println("证明synchronized是重入锁(迭代锁)");
        new Thread(phone::sendMSG, "thread1").start();
        new Thread(phone::sendMSG, "thread2").start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("证明ReentrantLock是重入锁(迭代锁)");
        new Thread(phone, "thread3").start();
        new Thread(phone, "thread4").start();
    }

}