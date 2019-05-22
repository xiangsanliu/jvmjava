package com.xiang.jvmjava.util;

/**
 * @author 项三六
 * @time 2019/4/18 22:48
 * @comment
 */

@FunctionalInterface
public interface Function<T> {
    void execute(T t);
}
