package com.zhoufan.thread.cas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CASTest
 * CAS是什么 比较并交换compareAndSet .
 *
 * @author Create in ZhouFan, 2019-07-26
 * @version v1.0
 */
public class CASTest {

    public static void main(String[] args) {
//        compareAndSetDemo();
    }

    /**
     * 在工作区中运算之后 还需要将主内存和存入工作区的初始值作比较
     * 值相同true 则进行更新 反之更新失败
     * 因此在多线程中为了保证原子性 会进行多次重复操作
     */
    public static void compareAndSetDemo() {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019) + "当前值为=" + atomicInteger);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "当前值为=" + atomicInteger);

    }

    public static void testBigDecimal() {
        BigDecimal a = new BigDecimal(4);
        BigDecimal b = new BigDecimal(5);

        a = a.add(b);
        System.out.println(a);
    }

    public static void localDateTest() {
        LocalDate parse = LocalDate.parse("20191001", DateTimeFormatter.ofPattern("yyyyMMdd"));

        System.out.println(parse.toString());

    }


}
