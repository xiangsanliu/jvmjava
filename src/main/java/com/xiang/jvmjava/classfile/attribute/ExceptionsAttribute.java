package com.xiang.jvmjava.classfile.attribute;

import com.xiang.jvmjava.classfile.AttributeInfo;
import com.xiang.jvmjava.classfile.ClassReader;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/16 10:48
 * @comment
 */

public class ExceptionsAttribute extends AttributeInfo {

    @Getter
    private int[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16s();
    }
}
