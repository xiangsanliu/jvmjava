package com.xiang.jvmjava.classfile;

import com.xiang.jvmjava.classfile.attribute.*;

import java.util.Objects;

/**
 * @author 项三六
 * @time 2019/3/16 10:03
 * @comment
 */

public abstract class AttributeInfo {

    public abstract void readInfo(ClassReader reader);

    protected static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int count = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[count];
        for (int i = 0; i < count; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    private static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrNameIndex = reader.readUint16();
        String attrName = constantPool.getUtf8(attrNameIndex);
        int attrLen = reader.readUint32();
        AttributeInfo attributeInfo = newAttributeInfo(attrName, attrLen, constantPool);
        Objects.requireNonNull(attributeInfo).readInfo(reader);
        return attributeInfo;
    }

    private static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool constantPool) {
        switch (attrName) {
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "Exceptions":
                return new ExceptionsAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return null;
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen);
        }
    }

}
