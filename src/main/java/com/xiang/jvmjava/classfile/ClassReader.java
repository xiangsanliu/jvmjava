package com.xiang.jvmjava.classfile;

import java.nio.ByteBuffer;

/**
 * @author 项三六
 * @time 2019/3/15 16:30
 * @comment
 */

public class ClassReader {

    private ByteBuffer data;

    private int index;

    ClassReader(byte[] data) {
        this.data = ByteBuffer.wrap(data);
        this.index = 0;
    }

    public int readUint8() {
        byte val = this.data.get(index);
        this.index++;
        return val;
    }

    public int readUint16() {
        short val = this.data.getShort(index);
        this.index += 2;
        return val;
    }

    public int readUint32() {
        int val = this.data.getInt(index);
        this.index += 4;
        return val;
    }

    public long readUint64() {
        long val = this.data.getLong(index);
        this.index += 8;
        return val;
    }

    public int[] readUint16s() {
        int n = readUint16();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = readUint16();
        }
        return s;
    }


}
