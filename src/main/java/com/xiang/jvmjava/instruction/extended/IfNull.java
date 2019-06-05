package com.xiang.jvmjava.instruction.extended;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.BranchInstruction;

/**
 * @author 项三六
 * @time 2019/3/22 16:09
 * @comment
 */

public class IfNull extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        if (frame.getOperandStack().popRef() == null) {
            branch(frame, offset);
        }
    }
}
