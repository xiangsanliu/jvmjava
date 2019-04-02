package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantInfo;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/4/2 10:25
 * @comment
 */

@Getter
public class ConstantMethodTypeInfo extends ConstantInfo {

    private int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
    }
}
