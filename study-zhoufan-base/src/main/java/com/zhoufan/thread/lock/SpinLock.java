/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁 SpinLock.
 *
 * @author Zhou Fan, 2019年12月19日
 * @version OPRA.1.0.0
 */

public class SpinLock {
    /**
     * 执行结果
     * t1加锁.....
     * t2加锁.....
     * t1解锁.....
     * t2解锁.....
     */
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {

        //当前线程
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "加锁.....");
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }


    public void unlock() {
        //当前线程
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "解锁.....");
    }

    public static void main(String[] args) {

        SpinLock lock = new SpinLock();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "t2").start();

    }
}