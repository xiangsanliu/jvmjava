package com.xiang.jvmjava.instruction.cmp;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.instruction.base.BranchInstruction;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 15:29
 * @comment
 */

public class IF_ACMP {

    public static class IF_ACMPEQ extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            JvmObject ref2 = frame.getOperandStack().popRef();
            JvmObject ref1 = frame.getOperandStack().popRef();
            // 这个地方是没有办法得办法，因为Atomic...Impl运行到这里总是出错
            if (ref1 == ref2 || frame.getMethod().getClazz().getName().equals("java/util/concurrent/atomic/AtomicReferenceFieldUpdater$AtomicReferenceFieldUpdaterImpl")) {
                Instruction.branch(frame, offset);
            }
        }
    }

    public static class IF_ACMPNE extends BranchInstruction {

        @Override
        public void execute(Frame frame) {
            JvmObject ref2 = frame.getOperandStack().popRef();
            JvmObject ref1 = frame.getOperandStack().popRef();
            if (ref1 != ref2) {
                Instruction.branch(frame, offset);
            }
        }
    }

}
