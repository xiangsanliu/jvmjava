package com.xiang.jvmjava.instruction.load;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:12
 * @comment
 */

public class DLoad {

    private static void dLoad(Frame frame, int index) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }

    public static class DLoadD extends Index8Instruction {

        @Override
        public void execute(Frame frame) {
            dLoad(frame, this.index);
        }
    }

    public static class DLoad0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dLoad(frame, 0);
        }
    }

    public static class DLoad1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dLoad(frame, 1);
        }
    }

    public static class DLoad2 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dLoad(frame, 2);
        }
    }

    public static class DLoad3 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dLoad(frame, 3);
        }
    }


}
