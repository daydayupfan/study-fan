/*
 * Copyright (c) ACCA Corp.
 * All Rights Reserved.
 */
package com.zhoufan.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * ReflectionMain.
 *
 * @author Zhou Fan, 2021年07月21日
 * @version OPRA. v.1.0.0
 */

public class ReflectionMain {
    private final static Person PERSON = new Person();

    public static void main(String[] args) {
//        getMethodAndInit();
        getTypeParameters();
    }

    /**
     * 获取数据并初始化
     */
    private static void getMethodAndInit() {
        try {
            Method setAge = PERSON.getClass().getDeclaredMethod("setAge", Integer.class);
            Method setName = PERSON.getClass().getDeclaredMethod("setName", String.class);
            Method setLike = PERSON.getClass().getDeclaredMethod("setLike", List.class);
            setAge.invoke(PERSON, 10);
            setName.invoke(PERSON, "zhoufan");
            List<String> like = new ArrayList<>();
            like.add("1");
            like.add("2");
            like.add("3");
            setLike.invoke(PERSON, like);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("反射 person 赋值对象" + PERSON.toString());
    }

    /**
     * 获取方法
     */
    private static void getMethods() {
        Method[] methods = Person.class.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("方法 [" + methods[i] + "]");
        }
    }


    /**
     * 获取PUBLIC 修饰符字段
     */
    private static void getFields() {
        Field[] fields = Person.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("字段 [" + fields[i] + "]");
        }
    }

    /**
     * 获取所有字段
     */
    private static void getDeclaredFields() {
        Field[] fields = Person.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("字段 [" + fields[i] + "]");
        }
    }

    /**
     * 获取所有字段类型
     */
    private static void getTypeParameters() {
        TypeVariable<Class<Person>>[] typeParameters = Person.class.getTypeParameters();
        for (TypeVariable<Class<Person>> typeParameter : typeParameters) {
            System.out.println(typeParameter.getName()+"  "+typeParameter.getTypeName());
        }
    }

}