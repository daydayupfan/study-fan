/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.atomicreference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用 ABA问题再现 以及解决
 * AtomicReferenceDemo.
 *
 * @author Zhou Fan, 2019年12月13日
 * @version OPRA.1.0.0
 */

public class AtomicReferenceDemo {

    private static AtomicReference<Long> longAtomicReference = new AtomicReference<>(10L);
    private static AtomicStampedReference<Long> longAtomicReference1 = new AtomicStampedReference<>(10L, 1);

    public static void main(String[] args) {
//        abaDisplay();
        abaSolve();

    }

    /**
     * 就像 你的女朋友离开你之后经历了别人 你不知道 后来又和你在一起 /手动狗头
     */
    public static void abaDisplay() {
        System.out.println("=============ABA问题重现===============");
        new Thread(() -> {
            System.out.println("thread1 执行第1次 " +
                    longAtomicReference.compareAndSet(10L, 20L) + " 结果=" + longAtomicReference.get());
            System.out.println("thread1 执行第2次 " +
                    longAtomicReference.compareAndSet(20L, 10L) + " 结果=" + longAtomicReference.get());

        }, "thread1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("thread2 执行第3次 " +
                        longAtomicReference.compareAndSet(10L, 20L) + " 结果=" + longAtomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();
    }

    /**
     * ABA问题解决 使用带时间戳（乐观锁 版本号）的源自引用类进行解决
     */
    public static void abaSolve() {
        System.out.println("==================aba问题解决===================");
        new Thread(() -> {
            int startStamp = longAtomicReference1.getStamp();
            try {
                System.out.println("thread3 初始 版本号=" + startStamp);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("thread3 执行第1次 " +
                        longAtomicReference1.compareAndSet(10L, 20L, longAtomicReference1.getStamp(), longAtomicReference1.getStamp() + 1) + " 版本号=" + longAtomicReference1.getStamp());
                System.out.println("thread3 执行第2次 " +
                        longAtomicReference1.compareAndSet(20L, 10L, longAtomicReference1.getStamp(), longAtomicReference1.getStamp() + 1) + " 版本号=" + longAtomicReference1.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread3").start();

        new Thread(() -> {
            int startStamp = longAtomicReference1.getStamp();
            System.out.println("thread4 初始 版本号=" + startStamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread4 第3次执行 =" +
                    longAtomicReference1.compareAndSet(10L, 20L, startStamp, startStamp + 1) + " 版本号=" + longAtomicReference1.getStamp());

            System.out.println(Thread.currentThread().getName() + " 最新值=" + longAtomicReference1.getReference());
        }, "thread4").start();

    }
}