package com.xiang.jvmjava.classfile.attribute;

import com.xiang.jvmjava.classfile.AttributeInfo;
import com.xiang.jvmjava.classfile.ClassReader;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/16 10:47
 * @comment
 */

public class ConstantValueAttribute extends AttributeInfo {

    @Getter
    private int constantValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }
}
