package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantInfo;
import com.xiang.jvmjava.classfile.ConstantPool;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/15 17:47
 * @comment
 */


public class ConstantClassInfo extends ConstantInfo {

    private ConstantPool constantPool;
    @Setter
    @Getter
    private int nameIndex;

    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
    }

    @Override
    public String toString() {
        return this.constantPool.getUtf8(this.nameIndex);
    }

    public String getName() {
        return this.constantPool.getUtf8(this.nameIndex);
    }
}
