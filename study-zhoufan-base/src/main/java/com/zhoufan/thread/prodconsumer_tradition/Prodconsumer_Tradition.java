/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.prodconsumer_tradition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统生产者消费者模式  生产一个用一个Prodconsumer_Tradition.
 *
 * @author Zhou Fan, 2019年12月31日
 * @version OPRA.1.0.0
 */

public class Prodconsumer_Tradition {

    public static void main(String[] args) {

        MyCooker box = new MyCooker();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                box.increment();
            }
        }, "thread1").start();


        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                box.decrement();
            }
        }, "thread2").start();
    }
}


class MyCooker {
    private int num;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            //等于0才进行叠加 不为0 阻塞
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "   " + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            //等于0才进行叠加 不为0 阻塞
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "   " + num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}