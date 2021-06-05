package com.zhoufan.volatilestudy;

/**
 * SingleModel
 *
 * 不加volatile叫做DCL单例模式.
 *
 * @author Create in ZhouFan, 2019-07-26
 * @version v1.0
 */
public class SingleModel {

    private volatile static SingleModel instance;

    private SingleModel(){
        System.out.println(Thread.currentThread().getName());
    }

    public static SingleModel getInstance(){
        if(instance==null){
            synchronized (SingleModel.class){
                if(instance==null){
                    //实例生成分三步 分配内存 初始化 地址赋值
                    //指令重排会导致未初始化就去调用功能 会产生报错
                    instance=new SingleModel();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000000 ; i++) {
            new Thread(()->{
                SingleModel.getInstance();
            },i+"").start();
        }
    }
}
