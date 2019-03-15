package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.util.Pair;
import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantInfo;
import com.xiang.jvmjava.classfile.ConstantPool;

/**
 * @author 项三六
 * @time 2019/3/15 19:20
 * @comment
 */

public class ConstantMemberRefInfo extends ConstantInfo {

    private ConstantPool constantPool;

    private int classIndex;

    private int nameAndTypeIndex;

    public ConstantMemberRefInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    public String className() {
        return this.constantPool.getClassName(this.classIndex);
    }

    public Pair<String, String> nameAndDescriptor() {
        return this.constantPool.getNameAndType(this.nameAndTypeIndex);
    }
}
