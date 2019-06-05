package com.xiang.jvmjava.instruction.cmp;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/21 14:01
 * @comment
 */

public class DCmp {

    private static void dCmp(Frame frame, boolean gFlag) {
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        if (v1 > v2) {
            stack.pushInt(1);
        } else if (v1 == v2) {
            stack.pushInt(0);
        } else if (v1 < v2) {
            stack.pushInt(-1);
        } else if (gFlag) {
            stack.pushInt(1);
        } else {
            stack.pushInt(-1);
        }
    }

    public static class DCmpG extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dCmp(frame, true);
        }
    }

    public static class DCmpL extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dCmp(frame, false);
        }
    }

}
