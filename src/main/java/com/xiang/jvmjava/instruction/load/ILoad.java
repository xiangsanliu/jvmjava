package com.xiang.jvmjava.instruction.load;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:00
 * @comment
 */

public class ILoad {

    private static void iLoad(Frame frame, int index) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }

    public static class ILoadI extends Index8Instruction {

        @Override
        public void execute(Frame frame) {
            iLoad(frame, this.index);
        }
    }

    public static class ILoad0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            iLoad(frame, 0);
        }
    }

    public static class ILoad1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            iLoad(frame, 1);
        }
    }

    public static class ILoad2 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            iLoad(frame, 2);
        }
    }

    public static class ILoad3 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            iLoad(frame, 3);
        }
    }

}
