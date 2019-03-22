package com.xiang.jvmjava.instruction.conversions;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/21 13:24
 * @comment
 */

public class L2X {

    public static class L2D extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long d = stack.popLong();
            stack.pushDouble((double) d);
        }
    }

    public static class L2I extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long d = stack.popLong();
            stack.pushInt((int) d);
        }
    }

    public static class L2F extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long d = stack.popLong();
            stack.pushFloat((float) d);
        }
    }

}
