package com.zhoufan.classinit;

/**
 * 1、类初始化过程
 * 2、实例初始化过程
 * 3、方法重写
 */
public class Son extends Father {

    private int i = test();

    private static int j = method();

    static {
        System.out.print("(6)");
    }

    Son() {
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }


    /**
     * 此处涉及重写
     *
     * @return
     */
    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }


    /**
     * 分析结果
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 父类静态成员
         * 父类静态代码块
         * 子类静态成员
         * 子类静态代码块
         * 父类非静态成员
         * 父类非静态代码块
         * 父类类构造方法
         * 子类非静态成员
         * 子类非静态代码块
         * 子类构造方法
         *
         * 静态、非静态的相同点： 成员 代码块是按顺序执行 谁在前谁先执行
         * 构造在最后
         */
        // (5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
        Son son = new Son();
        System.out.println();
        //(9)(3)(2)(9)(8)(7)
        Son son1 = new Son();
    }
}
