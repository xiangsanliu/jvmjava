package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:11
 * @comment
 */

public class ADD {

    public static class DAdd extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double v1 = stack.popDouble();
            double v2 = stack.popDouble();
            stack.pushDouble(v1 + v2);
        }
    }

    public static class FAdd extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            float v1 = stack.popFloat();
            float v2 = stack.popFloat();
            stack.pushFloat(v1 + v2);
        }
    }

    public static class IAdd extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v1 = stack.popInt();
            int v2 = stack.popInt();
            stack.pushInt(v1 + v2);
        }
    }

    public static class LAdd extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long v1 = stack.popLong();
            long v2 = stack.popLong();
            stack.pushLong(v1 + v2);
        }
    }

}
