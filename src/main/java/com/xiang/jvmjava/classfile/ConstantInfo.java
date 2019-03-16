package com.xiang.jvmjava.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.xiang.jvmjava.classfile.constantinfo.*;

/**
 * @author 项三六
 * @time 2019/3/15 17:11
 * @comment
 */

public abstract class ConstantInfo {

    private static final int ConstantClass = 7;
    private static final int ConstantFieldRef = 9;
    private static final int ConstantMethodRef = 10;
    private static final int ConstantInterfaceMethodRef = 11;
    private static final int ConstantString = 8;
    private static final int ConstantInteger = 3;
    private static final int ConstantFloat = 4;
    private static final int ConstantLong = 5;
    private static final int ConstantDouble = 6;
    private static final int ConstantNameAndType = 12;
    private static final int ConstantUtf8 = 1;
    private static final int ConstantMethodHandle = 15;
    private static final int ConstantMethodType = 16;
    private static final int ConstantInvokeDynamic = 18;

    public abstract void readInfo(ClassReader reader);

    static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool) {
        int tag = reader.readUint8();
        ConstantInfo constantInfo = newConstantInfo(tag, constantPool);
        constantInfo.readInfo(reader);
        return constantInfo;
    }

    private static ConstantInfo newConstantInfo(int tag, ConstantPool constantPool) {
        switch (tag) {
            case ConstantInteger:
                return new ConstantIntegerInfo();
            case ConstantFloat:
                return new ConstantFloatInfo();
            case ConstantLong:
                return new ConstantLongInfo();
            case ConstantDouble:
                return new ConstantDoubleInfo();
            case ConstantUtf8:
                return new ConstantUtf8Info();
            case ConstantString:
                return new ConstantStringInfo(constantPool);
            case ConstantClass:
                return new ConstantClassInfo(constantPool);
            case ConstantFieldRef:
                return new ConstantMemberRefInfo(constantPool);
            case ConstantMethodRef:
                return new ConstantMemberRefInfo(constantPool);
            case ConstantInterfaceMethodRef:
                return new ConstantMemberRefInfo(constantPool);
            case ConstantNameAndType:
                return new ConstantNameAndTypeInfo();
            case ConstantMethodType:
                System.out.println("null");
                return null;
            case ConstantMethodHandle:
                System.out.println("null");

                return null;
            case ConstantInvokeDynamic:
                System.out.println("null");

                return null;
            default:
                throw new ClassFormatException("constant pool tag");
        }
    }

}
