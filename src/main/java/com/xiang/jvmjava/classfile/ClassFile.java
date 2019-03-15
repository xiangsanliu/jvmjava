package com.xiang.jvmjava.classfile;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/15 16:25
 * @comment
 */

@Getter
@Setter
public class ClassFile {

    private int minorVersion;

    private int majorVersion;

    private ConstantPool constantPool;

    private int accessFlags;

    private int thisClass;

    private int superClass;

    private int[] interfaces;

    private Object[] fields;

    private Object[] methods;

    private Object[] AttributeInfo;

    public static ClassFile parse(byte[] classData) {
        ClassFile classFile = new ClassFile();
        ClassReader reader = new ClassReader(classData);
        classFile.read(reader);
        return new ClassFile();
    }

    private void read(ClassReader reader) {
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        this.constantPool = ConstantPool.readConstantPool(reader);
        this.accessFlags = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();

    }

    private void readAndCheckMagic(ClassReader reader) {
        int magic = reader.readUint32();
        if (magic != 0xCAFEBABE) {
            throw new ClassFormatException("Magic num err!");
        }
    }

    private void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (this.majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion == 0) {
                    return;
                }
        }
        throw new UnsupportedClassVersionError("minorVersion: " + this.minorVersion + ", majorVersion: " + this.majorVersion);
    }


}
