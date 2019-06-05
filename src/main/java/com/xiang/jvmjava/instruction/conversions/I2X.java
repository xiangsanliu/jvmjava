package com.xiang.jvmjava.instruction.conversions;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/21 13:19
 * @comment
 */

public class I2X {

    public static class I2B extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int d = stack.popInt();
            stack.pushInt((byte) d);
        }
    }

    public static class I2C extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int d = stack.popInt();
            stack.pushInt((char) d);
        }
    }

    public static class I2S extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int d = stack.popInt();
            stack.pushInt((short) d);
        }
    }

    public static class I2F extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int d = stack.popInt();
            stack.pushFloat((float) d);
        }
    }

    public static class I2D extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int d = stack.popInt();
            stack.pushDouble((double) d);
        }
    }

    public static class I2L extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int d = stack.popInt();
            stack.pushLong((long) d);
        }
    }


}
