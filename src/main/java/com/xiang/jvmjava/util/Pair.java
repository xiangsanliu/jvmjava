package com.xiang.jvmjava.util;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author 项三六
 * @time 2019/3/15 19:12
 * @comment
 */

@Getter
public class Pair<K, V> implements Serializable {

    private K key;

    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }
}
