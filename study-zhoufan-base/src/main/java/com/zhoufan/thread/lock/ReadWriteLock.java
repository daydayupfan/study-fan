/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class MyCache{
    private volatile Map<Integer, String> map= new HashMap<>();
    
    private ReentrantReadWriteLock lock =new ReentrantReadWriteLock();
    
    public String read(Integer key){
        System.out.println(Thread.currentThread().getName()+"    正在读取..");
        try { TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) { e.printStackTrace();}
        String value =map.get(key);
        System.out.println(Thread.currentThread().getName()+"    读取完成:"+value);
        return value;
    }

    public String read1(Integer key){
        lock.readLock().lock();
        String value=null;
        try {
            System.out.println(Thread.currentThread().getName()+"    正在读取..");
            TimeUnit.SECONDS.sleep(1);
            value =map.get(key);
            System.out.println(Thread.currentThread().getName()+"    读取完成:"+value);
        }catch (Exception e){

        }finally {
            lock.readLock().unlock();
        }
        return value;
    }


    public void write(Integer key, String value){
        System.out.println(Thread.currentThread().getName()+"     正在写入..");
        try { TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) { e.printStackTrace();}
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"     写入完成");
    }

    public void write1(Integer key, String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"     正在写入..");
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"     写入完成");
        }catch (Exception e){

        }finally {
            lock.writeLock().unlock();
        }
    }
}
/**
 * 读写锁 ReadWriteLock.
 *
 * @author Zhou Fan, 2019年12月20日
 * @version OPRA.1.0.0
 */

public class ReadWriteLock {
    
    public static void main(String[] args) {


        /**
         * 加锁前
         * writer0     正在写入..
         * writer4     正在写入..
         * writer3     正在写入..
         * writer2     正在写入..
         * writer1     正在写入..
         * 0    正在读取..
         * 1    正在读取..
         * 2    正在读取..
         * 3    正在读取..
         * 4    正在读取..
         * writer1     写入完成
         * writer3     写入完成
         * writer4     写入完成
         * 2    读取完成:2
         * 3    读取完成:null
         * writer2     写入完成
         * 0    读取完成:0
         * writer0     写入完成
         * 4    读取完成:null
         * 1    读取完成:null
         * 
         * 加锁后
         * writer0     正在写入..
         * writer0     写入完成
         * writer1     正在写入..
         * writer1     写入完成
         * writer2     正在写入..
         * writer2     写入完成
         * writer3     正在写入..
         * writer3     写入完成
         * writer4     正在写入..
         * writer4     写入完成
         * 0    正在读取..
         * 1    正在读取..
         * 2    正在读取..
         * 3    正在读取..
         * 4    正在读取..
         * 4    读取完成:4
         * 1    读取完成:1
         * 0    读取完成:0
         * 2    读取完成:2
         * 3    读取完成:3
         */

        MyCache cache=new MyCache();
//        noAddLock(cache);
        
        addLock(cache);
    }
    
    public static void noAddLock(MyCache cache){
        for (int i = 0;i < 5; i++){
            final Integer key=i;
            new Thread(()->{
                cache.write(key, key+"");
            }, "writer"+key).start();
        }

        for (int i = 0;i < 5; i++){
            final Integer key=i;
            new Thread(()->{
                String value=cache.read(key);
            }, key+"").start();
        }
    }
    
    public static void addLock(MyCache cache){
        for (int i = 0;i < 5; i++){
            final Integer key=i;
            new Thread(()->{
                cache.write1(key, key+"");
            }, "writer"+key).start();
        }

        for (int i = 0;i < 5; i++){
            final Integer key=i;
            new Thread(()->{
                String value=cache.read1(key);
            }, key+"").start();
        }
    }
    
}