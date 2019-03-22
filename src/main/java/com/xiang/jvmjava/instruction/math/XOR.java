package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 22:21
 * @comment
 */

public class XOR {

    public static class IXOR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v1 = stack.popInt();
            int v2 = stack.popInt();
            stack.pushInt(v1 ^ v2);
        }
    }

    public static class LXOR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long v1 = stack.popLong();
            long v2 = stack.popLong();
            stack.pushLong(v1 ^ v2);
        }
    }

}
