package com.xiang.jvmjava.classfile.attribute;

import com.xiang.jvmjava.classfile.AttributeInfo;
import com.xiang.jvmjava.classfile.ClassReader;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/16 10:57
 * @comment
 */

@Getter
public class UnparsedAttribute extends AttributeInfo {

    private String name;

    private int length;

    private byte[] info;

    public UnparsedAttribute(String name, int length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(this.length);
    }
}
