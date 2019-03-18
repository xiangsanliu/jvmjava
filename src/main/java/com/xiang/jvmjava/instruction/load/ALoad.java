package com.xiang.jvmjava.instruction.load;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:13
 * @comment
 */

public class ALoad {

    private static void aLoad(Frame frame, int index) {
        Object ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }

    public static class ALoadA extends Index8Instruction {

        @Override
        public void Execute(Frame frame) {
            aLoad(frame, this.index);
        }
    }

    public static class ALoad0 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            aLoad(frame, 0);
        }
    }

    public static class ALoad1 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            aLoad(frame, 1);
        }
    }

    public static class ALoad2 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            aLoad(frame, 2);
        }
    }

    public static class ALoad3 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            aLoad(frame, 3);
        }
    }

}
