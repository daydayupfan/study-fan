package com.zhoufan.lambdaExpress;

/**
 * 函数式接口
 * 添加上 @FunctionalInterface注解后 接口中就不能再添加其他接口方法了
 * @param <T>
 */
@FunctionalInterface
public interface Operation<T> {

    T add(T a, T b);

}
