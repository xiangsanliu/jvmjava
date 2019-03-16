package com.xiang.jvmjava.classfile.attribute;

import com.xiang.jvmjava.classfile.AttributeInfo;
import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantPool;

/**
 * @author 项三六
 * @time 2019/3/16 10:55
 * @comment
 */

public class SourceFileAttribute extends AttributeInfo {

    private ConstantPool constantPool;

    private int sourceFileIndex;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    public String getFileName() {
        return this.constantPool.getUtf8(this.sourceFileIndex);
    }

}
