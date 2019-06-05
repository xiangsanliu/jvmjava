package com.xiang.jvmjava.instruction.constants;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/17 20:02
 * @comment
 */

public class Const {

    public static class AConstNull extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushRef(null);
        }

    }

    public static class DConst0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushDouble(0D);
        }
    }

    public static class DConst1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushDouble(1D);
        }
    }

    public static class FConst0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushFloat(0F);
        }
    }

    public static class FConst1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushFloat(1F);
        }
    }

    public static class FConst2 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushFloat(2F);
        }
    }

    public static class IConstM1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(-1);
        }
    }

    public static class IConst0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(0);
        }
    }

    public static class IConst1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(1);
        }
    }

    public static class IConst2 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(2);
        }
    }

    public static class IConst3 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(3);
        }
    }

    public static class IConst4 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(4);
        }
    }

    public static class IConst5 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushInt(5);
        }
    }

    public static class LConst0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushLong(0L);
        }
    }

    public static class LConst1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            frame.getOperandStack().pushLong(1L);
        }
    }

}
