package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantLiteralInfo;
import com.xiang.jvmjava.classfile.ConstantPool;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/15 17:48
 * @comment
 */

public class ConstantStringInfo extends ConstantLiteralInfo {

    private ConstantPool constantPool;

    @Getter
    @Setter
    private int stringIndex;

    public ConstantStringInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
    }

    @Override
    public String toString() {
        return this.constantPool.getUtf8(this.stringIndex);
    }

    @Override
    public Object value() {
        return toString();
    }
}
