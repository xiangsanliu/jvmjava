package com.xiang.jvmjava.instruction.cmp;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/21 14:09
 * @comment
 */

public class FCmp {

    private static void fCmp(Frame frame, boolean gFlag) {
        OperandStack stack = frame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();
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

    public static class FCmpG extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            fCmp(frame, true);
        }
    }

    public static class FCmpL extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            fCmp(frame, false);
        }
    }

}
