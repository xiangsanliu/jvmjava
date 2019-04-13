package com.xiang.jvmjava.classfile.attribute;

import com.xiang.jvmjava.classfile.AttributeInfo;
import com.xiang.jvmjava.classfile.ClassReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/16 10:50
 * @comment
 */

public class LineNumberTableAttribute extends AttributeInfo {

    private LineNumberTableEntry[] lineNumberTable;

    @Getter
    @Setter
    private class LineNumberTableEntry {

        private int startPC;

        private int lineNumber;

    }

    @Override
    public void readInfo(ClassReader reader) {
        int count = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[count];
        for (int i = 0; i < count; i++) {
            this.lineNumberTable[i] = new LineNumberTableEntry();
            this.lineNumberTable[i].setStartPC(reader.readUint16());
            this.lineNumberTable[i].setLineNumber(reader.readUint16());
        }
    }

    public int getLineNumber(int pc) {
        for (int i = this.lineNumberTable.length - 1; i >= 0; i--) {
            LineNumberTableEntry entry = this.lineNumberTable[i];
            if (pc >= entry.startPC) {
                return entry.lineNumber;
            }
        }
        return -1;
    }
}
