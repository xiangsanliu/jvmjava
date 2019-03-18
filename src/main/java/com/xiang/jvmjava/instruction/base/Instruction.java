package com.xiang.jvmjava.instruction.base;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.constants.Const;
import com.xiang.jvmjava.instruction.constants.IPush;
import com.xiang.jvmjava.instruction.constants.NOP;

/**
 * @author 项三六
 * @time 2019/3/17 17:44
 * @comment
 */

public abstract class Instruction {

    public abstract void fetchOperands(BytecodeReader reader);

    public abstract void Execute(Frame frame);

    public Instruction newInstruction(byte opcode) {
        switch (opcode) {
            case 0x00:
                return new NOP();
            case 0x01:
                return new Const.AConstNull();
            case 0x02:
                return new Const.IConstM1();
            case 0x03:
                return new Const.IConst0();
            case 0x04:
                return new Const.IConst1();
            case 0x05:
                return new Const.IConst2();
            case 0x06:
                return new Const.IConst3();
            case 0x07:
                return new Const.IConst4();
            case 0x08:
                return new Const.IConst5();
            case 0x09:
                return new Const.LConst0();
            case 0x0a:
                return new Const.LConst1();
            case 0x0b:
                return new Const.FConst0();
            case 0x0c:
                return new Const.FConst1();
            case 0x0d:
                return new Const.FConst2();
            case 0x0e:
                return new Const.DConst0();
            case 0x0f:
                return new Const.DConst1();
            case 0x10:
                return new IPush.BIPush();
            case 0x11:
                return new IPush.SIPush();
            default:
                throw new UnsupportedOperationException();
        }
    }

}
