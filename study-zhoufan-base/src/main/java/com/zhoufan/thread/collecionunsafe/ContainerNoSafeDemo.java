/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.collecionunsafe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ContainerNoSafeDemo.
 *
 * @author Zhou Fan, 2019年12月13日
 * @version OPRA.1.0.0
 */

public class ContainerNoSafeDemo {


    public static void main(String[] args) {
        set();
    }
    
    /**
     * 1.故障现象
     *          java.util.ConcurrentModificationException
     *
     * 2.并发争抢修改导致，一个正在写入，一个过来争夺资源，
     *   导致数据不一致发生异常
     *
     * 3.解决方案
     *     3.1.vector 效率低
     *     3.2.Collections.synchronizedList
     *     3.3.CopyOnWriteArrayList
     *     3.4:CopyOnWriteArraySet
     *     3.5:ConcurrentHashMap
     * 
     */
    public static void list(){
        listWay1();
        listWay2();
        listWay3();
    }
    
    public static void listWay1(){
        
        List<String> list=new Vector<>();
        common(list);
    }
    
    
    public static void listWay2(){
        List<String> list= Collections.synchronizedList(new ArrayList<>());
        common(list);
    }

    public static void listWay3(){
        List<String> list=new CopyOnWriteArrayList();
        common(list);
    }
    
    private static void  common(Collection<String> list){
        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, "thread"+i).start();
        }
    }


    public static void set(){
        Set<String> set = new CopyOnWriteArraySet<>();
        common(set);
    }
}