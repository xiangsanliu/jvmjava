package com.xiang.jvmjava.classfile;

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
                return new ConstantFieldRefInfo(constantPool);
            case ConstantMethodRef:
                return new ConstantMethodRefInfo(constantPool);
            case ConstantInterfaceMethodRef:
                return new ConstantInterfaceMethodRefInfo(constantPool);
            case ConstantNameAndType:
                return new ConstantNameAndTypeInfo();
            case ConstantMethodType:
                return new ConstantMethodTypeInfo();
            case ConstantMethodHandle:
                return new ConstantMethodHandleInfo();
            case ConstantInvokeDynamic:
                return new ConstantInvokeDynamicInfo();
            default:
                throw new ClassFormatError("constant pool tag");
        }
    }

    public abstract void readInfo(ClassReader reader);

}
