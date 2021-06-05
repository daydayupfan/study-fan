package com.zhoufan.diguianddiedai;

/**
 * 递归和迭代
 * 相同计算 使用迭代 效率高 资源占用少
 */
public class Demo {
    public static void main(String[] args) {
        int n = 40;
        digui(n);
        diedai(n);
    }

    /**
     * 递归方式
     *
     * @param n
     */
    public static void digui(int n) {
        long start = System.currentTimeMillis();
        System.out.println(foo(n));
        long end = System.currentTimeMillis();
        System.out.println("递归执行时间:" + (end - start));//递归 352
    }

    /**
     * 迭代方式
     *
     * @param n
     */
    public static void diedai(int n) {
        long start = System.currentTimeMillis();
        System.out.println(loop(n));
        long end = System.currentTimeMillis();
        System.out.println("迭代执行时间:" + (end - start));
    }

    /**
     * 递归方式 效率较低 资源消耗多
     *
     * @param n
     * @return
     */
    public static long foo(int n) {
        if (1 > n) {
            throw new IllegalArgumentException("参数不能小于" + n);
        }
        /**
         * 1-->1
         * 2-->2
         * 3-->3
         * 4-->5
         * 5-->8
         */
        if (1 == n || 2 == n) {
            return n;
        }
        return foo(n - 2) + foo(n - 1);
    }

    /**
     * 迭代方式 效率高 资源消耗少
     *
     * @param n
     * @return
     */
    public static long loop(int n) {
        if (1 > n) {
            throw new IllegalArgumentException("参数不能小于" + n);
        }
        /**
         * 1-->1
         * 2-->2
         * 3-->3
         * 4-->5
         * 5-->8
         */
        if (1 == n || 2 == n) {
            return n;
        }

        long sum = 0;
        long f1 = 1;
        long f2 = 2;

        for (int i = 3; i <= n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }

}
