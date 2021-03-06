package com.xiang.jvmjava.instruction.stack;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:38
 * @comment pop top1 element
 */

public class POP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popSlot();
    }
}
