package com.xiang.jvmjava.instruction.store;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:23
 * @comment
 */

public class DStore {

    private static void dStore(Frame frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }

    public static class DStoreD extends Index8Instruction {

        @Override
        public void execute(Frame frame) {
            dStore(frame, this.index);
        }
    }

    public static class DStore0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dStore(frame, 0);
        }
    }

    public static class DStore1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dStore(frame, 1);
        }
    }

    public static class DStore2 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dStore(frame, 2);
        }
    }

    public static class DStore3 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            dStore(frame, 3);
        }
    }


}
