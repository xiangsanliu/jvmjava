package com.xiang.jvmjava.instruction.load;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:10
 * @comment
 */

public class FLoad {

    private static void fLoad(Frame frame, int index) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }

    public static class FLoadF extends Index8Instruction {

        @Override
        public void execute(Frame frame) {
            fLoad(frame, this.index);
        }
    }

    public static class FLoad0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            fLoad(frame, 0);
        }
    }

    public static class FLoad1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            fLoad(frame, 1);
        }
    }

    public static class FLoad2 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            fLoad(frame, 2);
        }
    }

    public static class FLoad3 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            fLoad(frame, 3);
        }
    }


}
