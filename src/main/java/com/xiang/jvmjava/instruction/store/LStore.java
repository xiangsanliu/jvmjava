package com.xiang.jvmjava.instruction.store;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:32
 * @comment
 */

public class LStore {

    private static void lStore(Frame frame, int index) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }

    public static class LStoreL extends Index8Instruction {

        @Override
        public void Execute(Frame frame) {
            lStore(frame, this.index);
        }
    }

    public static class LStore0 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lStore(frame, 0);
        }
    }

    public static class LStore1 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lStore(frame, 1);
        }
    }

    public static class LStore2 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lStore(frame, 2);
        }
    }

    public static class LStore3 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lStore(frame, 3);
        }
    }


}
