package com.xiang.jvmjava.instruction.cmp;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.BranchInstruction;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 15:18
 * @comment
 */

public class IfCond {

    public static class IFEQ extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            if (frame.getOperandStack().popInt() == 0) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IFNE extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            if (frame.getOperandStack().popInt() != 0) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IFLT extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            if (frame.getOperandStack().popInt() < 0) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IFLE extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            if (frame.getOperandStack().popInt() <= 0) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IFGT extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            if (frame.getOperandStack().popInt() > 0) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IFGE extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            if (frame.getOperandStack().popInt() >= 0) {
                Instruction.branch(frame, offset);
            }
        }
    }
}
