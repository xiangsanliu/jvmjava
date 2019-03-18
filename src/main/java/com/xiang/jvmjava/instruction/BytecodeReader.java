package com.xiang.jvmjava.instruction;

import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author 项三六
 * @time 2019/3/17 17:49
 * @comment
 */

public class BytecodeReader {

    private ByteBuffer code;

    @Getter
    private int pc;

    public void reset(byte[] code, int pc) {
        this.code = ByteBuffer.wrap(code);
        this.pc = pc;
    }

    public byte readInt8() {
        return this.code.get(this.pc++);
    }

    public int readUint8() {
        return Byte.toUnsignedInt(readInt8());
    }

    public short readInt16() {
        short val = this.code.getShort(this.pc);
        this.pc += 2;
        return val;
    }

    public int readUint16() {
        return Short.toUnsignedInt(readInt16());
    }

    public int readInt32() {
        int val = this.code.getInt(this.pc);
        this.pc += 4;
        return val;
    }

    public long readUint32() {
        return Integer.toUnsignedLong(readInt32());
    }

    public int[] readInt32s(int n) {
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = this.readInt32();
        }
        return ints;
    }

    public void skipPadding() {
        while (this.pc % 4 != 0) {
            readUint8();
        }
    }


}
