package com.xiang.jvmjava.instruction.control.retrun;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/31 20:45
 * @comment
 */

public class DRetrun extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Frame currentFrame = frame.getThread().popFrame();
        Frame invokerFrame = frame.getThread().topFrame();
        invokerFrame.getOperandStack().pushDouble(currentFrame.getOperandStack().popDouble());
    }
}
