package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:30
 * @comment
 */

public class OR {

    public static class IOR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            stack.pushInt(v1 | v2);
        }
    }

    public static class LOR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long v2 = stack.popLong();
            long v1 = stack.popLong();
            stack.pushLong(v1 | v2);
        }
    }


}
