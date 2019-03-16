package com.xiang.jvmjava.classfile;

import lombok.Getter;
import lombok.ToString;

/**
 * @author 项三六
 * @time 2019/3/16 9:35
 * @comment
 */

public class MemberInfo {

    private ConstantPool constantPool;

    @Getter
    private int accessFlags;

    private int nameIndex;

    private int descriptorIndex;

    @Getter
    private Object[] attributes;

    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool) {
        int count = reader.readUint16();
        MemberInfo[] memberInfos = new MemberInfo[count];
        for (int i = 0; i < count; i++) {
            memberInfos[i] = new MemberInfo(reader, constantPool);
        }
        return memberInfos;
    }

    private MemberInfo(ClassReader reader, ConstantPool constantPool) {
        this.constantPool = constantPool;
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
        this.attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    public String getName() {
        return this.constantPool.getUtf8(this.nameIndex);
    }

    public String getDescriptor() {
        return this.constantPool.getUtf8(this.descriptorIndex);
    }
}
