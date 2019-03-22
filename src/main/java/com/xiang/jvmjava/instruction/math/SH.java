package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 21:39
 * @comment
 */

public class SH {

    public static class ISHL extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            long dis = Integer.toUnsignedLong(v2) & 0x1f;
            stack.pushInt(v1 << dis);
        }
    }

    public static class ISHR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            long dis = Integer.toUnsignedLong(v2) & 0x1f;
            stack.pushInt(v1 >> dis);
        }
    }

    public static class IUSHR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            int v1 = stack.popInt();
            long dis = Integer.toUnsignedLong(v2) & 0x1f;
            stack.pushInt(v1 >> dis);
        }
    }

    public static class LSHL extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            long v1 = stack.popLong();
            long dis = Integer.toUnsignedLong(v2) & 0x3f;
            stack.pushLong(v1 << dis);
        }
    }

    public static class LSHR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            long v1 = stack.popLong();
            long dis = Integer.toUnsignedLong(v2) & 0x3f;
            stack.pushLong(v1 >> dis);
        }
    }

    public static class LUSHR extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int v2 = stack.popInt();
            long v1 = stack.popLong();
            long dis = Integer.toUnsignedLong(v2) & 0x3f;
            stack.pushLong(v1 >> dis);
        }
    }

}
