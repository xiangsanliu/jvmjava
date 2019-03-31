package com.xiang.jvmjava.classfile.attribute;

import com.xiang.jvmjava.classfile.AttributeInfo;
import com.xiang.jvmjava.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/31 20:12
 * @comment
 */

public class LocalVariableTableAttribute extends AttributeInfo {

    private LocalVariableTableEntry[] localVariableTable;

    @Override
    public void readInfo(ClassReader reader) {
        int len = reader.readUint16();
        this.localVariableTable = new LocalVariableTableEntry[len];
        for (int i = 0; i < len; i++) {
            this.localVariableTable[i] = new LocalVariableTableEntry();
            this.localVariableTable[i].setStartPc(reader.readUint16());
            this.localVariableTable[i].setLength(reader.readUint16());
            this.localVariableTable[i].setNameIndex(reader.readUint16());
            this.localVariableTable[i].setDescriptorIndex(reader.readUint16());
            this.localVariableTable[i].setIndex(reader.readUint16());
        }
    }

    @Getter
    @Setter
    private class LocalVariableTableEntry {
        private int startPc;
        private int length;
        private int nameIndex;
        private int descriptorIndex;
        private int index;
    }

}
