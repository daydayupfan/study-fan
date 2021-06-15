package com.zhoufan.thread.collecionunsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HashMapThread 用于1.7HashMap测试多线程扩容时死锁.
 *
 * @author Zhou Fan, 2021年06月09日
 * @version OPRA. v.1.0.0
 */

public class HashMapThread extends Thread {

    private static AtomicInteger ai = new AtomicInteger();
    private static Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void run() {
        while (ai.get() < 1000000) {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }
    }
}