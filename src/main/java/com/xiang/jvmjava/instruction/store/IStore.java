package com.xiang.jvmjava.instruction.store;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:30
 * @comment
 */

public class IStore {

    private static void iStore(Frame frame, int index) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }

    public static class IStoreI extends Index8Instruction {

        @Override
        public void Execute(Frame frame) {
            iStore(frame, this.index);
        }
    }

    public static class IStore0 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            iStore(frame, 0);
        }
    }

    public static class IStore1 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            iStore(frame, 1);
        }
    }

    public static class IStore2 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            iStore(frame, 2);
        }
    }

    public static class IStore3 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            iStore(frame, 3);
        }
    }

}
