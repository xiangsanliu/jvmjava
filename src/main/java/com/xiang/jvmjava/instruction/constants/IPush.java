package com.xiang.jvmjava.instruction.constants;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/17 20:31
 * @comment
 */

public class IPush {

    public static class BIPush extends Instruction {

        private int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            this.val = reader.readInt8();
        }

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(this.val);
        }

    }

    public static class SIPush extends Instruction {

        private int val;

        @Override
        public void fetchOperands(BytecodeReader reader) {
            this.val = reader.readInt16();
        }

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(this.val);
        }

    }

}
