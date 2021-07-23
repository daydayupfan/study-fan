/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.thread.unsafe;

import sun.misc.Unsafe;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UnsafeTest.
 *
 * @author Zhou Fan, 2021年07月14日
 * @version OPRA. v.1.0.0
 */

public class UnsafeTest {

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = reflectGetUnsafe();
        Student student = new Student();
        System.out.println(student);
        Field name = student.getClass().getDeclaredField("name");
        long objectFieldOffsetOffset = unsafe.objectFieldOffset(name);
        unsafe.putObjectVolatile(student, objectFieldOffsetOffset, "abc");
        System.out.println(student);
    }
}