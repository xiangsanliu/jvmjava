package com.xiang.jvmjava.instruction.cmp;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.BranchInstruction;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 15:25
 * @comment
 */

public class IF_ICMP {

    public static class IF_ICMPEQ extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            int v2 = frame.getOperandStack().popInt();
            int v1 = frame.getOperandStack().popInt();
            if (v1 == v2) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IF_ICMPNE extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            int v2 = frame.getOperandStack().popInt();
            int v1 = frame.getOperandStack().popInt();
            if (v1 != v2) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IF_ICMPLT extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            int v2 = frame.getOperandStack().popInt();
            int v1 = frame.getOperandStack().popInt();
            if (v1 < v2) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IF_ICMPLE extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            int v2 = frame.getOperandStack().popInt();
            int v1 = frame.getOperandStack().popInt();
            if (v1 <= v2) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IF_ICMPGT extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            int v2 = frame.getOperandStack().popInt();
            int v1 = frame.getOperandStack().popInt();
            if (v1 > v2) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IF_ICMPGE extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            int v2 = frame.getOperandStack().popInt();
            int v1 = frame.getOperandStack().popInt();
            if (v1 >= v2) {
                Instruction.branch(frame, offset);
            }
        }
    }

}
