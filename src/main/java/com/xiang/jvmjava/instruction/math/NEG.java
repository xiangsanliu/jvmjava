package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:28
 * @comment
 */

public class NEG {

    public static class DNeg extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushDouble(-stack.popDouble());
        }
    }

    public static class FNeg extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushFloat(-stack.popFloat());
        }
    }

    public static class INeg extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushInt(-stack.popInt());
        }
    }

    public static class LNeg extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            stack.pushLong(-stack.popLong());
        }
    }

}
