package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 22:04
 * @comment
 */

public class SUB {

    public static class DSub extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double v2 = stack.popDouble();
            double v1 = stack.popDouble();
            stack.pushDouble(v1 - v2);
        }
    }

    public static class FSub extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            float v2 = stack.popFloat();
            float v1 = stack.popFloat();
            stack.pushFloat(v1 - v2);
        }
    }

    public static class ISub extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            stack.pushInt(v1 - v2);
        }
    }

    public static class LSub extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            stack.pushLong(v1 - v2);
        }
    }

}
