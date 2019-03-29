package com.xiang.jvmjava.instruction.store;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:20
 * @comment
 */

public class AStore {

    private static void aStore(Frame frame, int index) {
        JvmObject ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, ref);
    }

    public static class AStoreA extends Index8Instruction {

        @Override
        public void execute(Frame frame) {
            aStore(frame, this.index);
        }
    }

    public static class AStore0 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            aStore(frame, 0);
        }
    }

    public static class AStore1 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            aStore(frame, 1);
        }
    }

    public static class AStore2 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            aStore(frame, 2);
        }
    }

    public static class AStore3 extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            aStore(frame, 3);
        }
    }

}
