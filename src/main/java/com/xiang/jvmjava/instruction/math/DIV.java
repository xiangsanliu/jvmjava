package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:17
 * @comment
 */

public class DIV {

    public static class DDiv extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double v2 = stack.popDouble();
            double v1 = stack.popDouble();
            stack.pushDouble(v1 / v2);
        }
    }

    public static class FDiv extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            float v2 = stack.popFloat();
            float v1 = stack.popFloat();
            stack.pushFloat(v1 / v2);
        }
    }

    public static class IDiv extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            if (0 == v2) {
                throw new ArithmeticException("/ by zero");
            }
            int v1 = stack.popInt();
            stack.pushInt(v1 / v2);
        }
    }

    public static class LDiv extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            if (0 == v2) {
                throw new ArithmeticException("/ by zero");
            }
            long v1 = stack.popLong();
            stack.pushLong(v1 / v2);
        }
    }

}
