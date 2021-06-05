package com.zhoufan.volatilestudy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Create in ZhouFan, 2019-07-25
 * @version v1.0
 */

class MyData{
    int anInt = 0;
//     volatile int anInt = 0;

    public void changeA(){
        anInt=80;
    }

    public void addPlusPlus(){
        anInt++;
    }
}

/**
 *  原子性 要么都成功 要么都失败
 *  可见性
 */
public class VolatileDemo {

    public static void main(String[] args) {
        atomicTest();
    }


    /**
     * 验证volatile不具有原子性 以及解决原子性的提供方案
     */
    public static void atomicTest(){
        MyData myData=new MyData();

        //具有原子性的整型类
        AtomicInteger atomicInteger=new AtomicInteger();

        for (int i = 0; i <20 ; i++) {
            new  Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addPlusPlus();
                    atomicInteger.getAndIncrement();
                }

            },i+"").start();
        }

        //活动线程大于2时表示还未执行完成  继续等待.
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("执行完之后anint的数值="+myData.anInt);
        System.out.println("执行完之后anint的数值="+atomicInteger);

    }

    /**
     * VolatileDemo volatile 可见性测试
     *  不加volatile没有可见性 加上具有可见性
     * volatile 增强了主内存与线程工作区数据（副本）之间的同步.
     */
    public static void seeOkVolatile(){
        MyData myData=new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"进入线程开始操作");

            try {
                TimeUnit.SECONDS.sleep(3);
                myData.changeA();
                System.out.println(Thread.currentThread().getName()+"myData 执行结束。。  "+myData.anInt);
            }catch (Exception e){
                System.out.println(e);
            }
        },"abc").start();

        while(myData.anInt!=80){}
        System.out.println(Thread.currentThread().getName()+"可见性完成"+myData.anInt);
    }


    /**
     * 禁止指令重排
     */
    public static void stopOrderReSort(){

    }
}
