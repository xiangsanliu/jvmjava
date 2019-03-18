package com.xiang.jvmjava.instruction.store;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:27
 * @comment
 */

public class FStore {

    private static void fStore(Frame frame, int index) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }

    public static class FStoreF extends Index8Instruction {

        @Override
        public void Execute(Frame frame) {
            fStore(frame, this.index);
        }
    }

    public static class FStore0 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            fStore(frame, 0);
        }
    }

    public static class FStore1 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            fStore(frame, 1);
        }
    }

    public static class FStore2 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            fStore(frame, 2);
        }
    }

    public static class FStore3 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            fStore(frame, 3);
        }
    }


}
