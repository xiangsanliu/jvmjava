package com.xiang.jvmjava.instruction.conversions;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/20 18:51
 * @comment
 */

public class D2X {

    public static class D2F extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double d = stack.popDouble();
            stack.pushFloat((float) d);
        }
    }

    public static class D2I extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double d = stack.popDouble();
            stack.pushInt((int) d);
        }
    }

    public static class D2L extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double d = stack.popDouble();
            stack.pushLong((long) d);
        }
    }
}
