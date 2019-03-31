package com.xiang.jvmjava.instruction.control.retrun;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/31 20:42
 * @comment
 */

public class ARetrun extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.getThread().popFrame();
        Frame invokerFrame = frame.getThread().topFrame();
        invokerFrame.getOperandStack().pushRef(currentFrame.getOperandStack().popRef());
    }
}
