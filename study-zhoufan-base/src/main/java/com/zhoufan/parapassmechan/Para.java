package com.zhoufan.parapassmechan;

import java.util.Arrays;

/**
 * String 和基本数据包装类都是final修饰的 地址不变
 */
public class Para {
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1, 2, 3, 4, 5};
        MyData data = new MyData();
        change(i, str, num, arr, data);
        System.out.println("i=" + i);//1
        System.out.println("str=" + str);//hello
        System.out.println("num=" + num);//201  ? 应该为200
        System.out.println("arr=" + Arrays.toString(arr));//2 2 3 4 5
        System.out.println("data.a=" + data.a);//11
    }

    public static void change(int j, String s, Integer n, int[] arr, MyData data) {
        j += 1;
        s += "World";
        n += 1;
        arr[0] += 1;
        data.a += 1;
    }

}


class MyData {
    int a = 10;
}