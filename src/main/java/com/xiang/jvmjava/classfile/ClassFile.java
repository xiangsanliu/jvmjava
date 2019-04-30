package com.xiang.jvmjava.classfile;

import com.xiang.jvmjava.classfile.attribute.SourceFileAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 项三六
 * @time 2019/3/15 16:25
 * @comment
 */

@Getter
@Setter
@ToString
public class ClassFile {

    private int minorVersion;

    private int majorVersion;

    private ConstantPool constantPool;

    private int accessFlags;

    private int thisClass;

    private int superClass;

    private int[] interfaces;

    private MemberInfo[] fields;

    private MemberInfo[] methods;

    private AttributeInfo[] attributes;

    public static ClassFile parse(byte[] classData) {
        ClassFile classFile = new ClassFile();
        ClassReader reader = new ClassReader(classData);
        classFile.read(reader);
        return classFile;
    }

    private void read(ClassReader reader) {
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        this.constantPool = ConstantPool.readConstantPool(reader);
        this.accessFlags = reader.readUint16();
        this.thisClass = reader.readUint16();
        this.superClass = reader.readUint16();
        this.interfaces = reader.readUint16s();
        this.fields = MemberInfo.readMembers(reader, this.constantPool);
        this.methods = MemberInfo.readMembers(reader, this.constantPool);
        this.attributes = AttributeInfo.readAttributes(reader, this.constantPool);
    }

    private void readAndCheckMagic(ClassReader reader) {
        int magic = reader.readUint32();
        if (magic != 0xCAFEBABE) {
            throw new ClassFormatError("Magic num err!");
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
            case 53:
            case 54:
            case 55:
            case 56:
                if (this.minorVersion == 0) {
                    return;
                }
        }
        throw new UnsupportedClassVersionError("minorVersion: " + this.minorVersion + ", majorVersion: " + this.majorVersion);
    }

    public String getClassName() {
        return this.constantPool.getClassName(this.thisClass);
    }

    public String getSuperClassName() {
        if (this.superClass > 0) {
            return this.constantPool.getClassName(this.superClass);
        }
        return "";
    }

    public String[] getInterfaceNames() {
        String[] interfaceNames = new String[this.interfaces.length];
        for (int i = 0; i < this.interfaces.length; i++) {
            interfaceNames[i] = this.constantPool.getClassName(this.interfaces[i]);
        }
        return interfaceNames;
    }

    public SourceFileAttribute getSourceFileAttribute() {
        for (AttributeInfo info : this.attributes) {
            if (info instanceof SourceFileAttribute) {
                return (SourceFileAttribute) info;
            }
        }
        return null;
    }

}
