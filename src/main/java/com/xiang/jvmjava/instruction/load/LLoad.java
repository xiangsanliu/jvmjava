package com.xiang.jvmjava.instruction.load;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index8Instruction;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:06
 * @comment
 */

public class LLoad {

    private static void lLoad(Frame frame, int index) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }

    public static class LLoadL extends Index8Instruction {

        @Override
        public void Execute(Frame frame) {
            lLoad(frame, this.index);
        }
    }

    public static class LLoad0 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lLoad(frame, 0);
        }
    }

    public static class LLoad1 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lLoad(frame, 1);
        }
    }

    public static class LLoad2 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lLoad(frame, 2);
        }
    }

    public static class LLoad3 extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            lLoad(frame, 3);
        }
    }

}
