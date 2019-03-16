package com.xiang.jvmjava.classfile.attribute;

import com.xiang.jvmjava.classfile.AttributeInfo;
import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantPool;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/16 10:16
 * @comment
 */

public class CodeAttribute extends AttributeInfo {

    private ConstantPool constantPool;

    private int maxStack;

    private int maxLocals;

    private byte[] code;

    private ExceptionTableEntry[] exceptionTable;

    private AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Getter
    @Setter
    private class ExceptionTableEntry {

        private int startPC;

        private int endPC;

        private int handlerPC;

        private int catchType;

    }

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        int codeLength = reader.readUint32();
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = readExceptionTables(reader);
        this.attributes = readAttributes(reader, constantPool);
    }

    private ExceptionTableEntry[] readExceptionTables(ClassReader reader) {
        int count = reader.readUint16();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[count];
        for (int i = 0; i < count; i++) {
            exceptionTable[i] = new ExceptionTableEntry();
            exceptionTable[i].setStartPC(reader.readUint16());
            exceptionTable[i].setEndPC(reader.readUint16());
            exceptionTable[i].setHandlerPC(reader.readUint16());
            exceptionTable[i].setCatchType(reader.readUint16());
        }
        return exceptionTable;
    }


}
