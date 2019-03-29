package com.xiang.jvmjava.classfile;

import com.xiang.jvmjava.classfile.constantinfo.*;
import com.xiang.jvmjava.error.InvalidConstantPoolIndexError;
import com.xiang.jvmjava.util.Pair;
import lombok.Getter;
import lombok.ToString;

/**
 * @author 项三六
 * @time 2019/3/15 17:14
 * @comment 常量池
 */

@ToString
public class ConstantPool {

    @Getter
    private ConstantInfo[] constantInfos;

    private ConstantPool(ConstantInfo[] constantInfos) {
        this.constantInfos = constantInfos;
    }

    static ConstantPool readConstantPool(ClassReader reader) {
        int count = reader.readUint16();
        ConstantPool constantPool = new ConstantPool(new ConstantInfo[count]);
        ConstantInfo[] constantInfos = constantPool.constantInfos;
        for (int i = 1; i < count; i++) {
            constantInfos[i] = ConstantInfo.readConstantInfo(reader, constantPool);
            if (constantInfos[i] instanceof ConstantLongInfo || constantInfos[i] instanceof ConstantDoubleInfo) {
                i++;
            }
        }
        return constantPool;
    }

    public ConstantInfo getConstantInfo(int index) {
        ConstantInfo constantInfo = this.constantInfos[index];
        if (constantInfo != null) {
            return constantInfo;
        }
        throw new InvalidConstantPoolIndexError();
    }

    public Pair<String, String> getNameAndType(int index) {
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        String name = getUtf8(info.getNameIndex());
        String type = getUtf8(info.getDescriptorIndex());
        return new Pair<>(name, type);
    }

    public String getClassName(int index) {
        ConstantClassInfo info = (ConstantClassInfo) getConstantInfo(index);
        return getUtf8(info.getNameIndex());
    }

    public String getUtf8(int index) {
        return ((ConstantUtf8Info) this.getConstantInfo(index)).getStr();
    }

}
