package com.xiang.jvmjava.instruction.conversions;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/21 13:17
 * @comment
 */

public class F2X {

    public static class F2D extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            float d = stack.popFloat();
            stack.pushDouble((double) d);
        }
    }

    public static class F2I extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            float d = stack.popFloat();
            stack.pushInt((int) d);
        }
    }

    public static class F2L extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            float d = stack.popFloat();
            stack.pushLong((long) d);
        }
    }

}
