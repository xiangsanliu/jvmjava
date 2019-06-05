package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/4/13 17:20
 * @comment
 */

public class MonitorExit extends NoOperandsInstruction {


    @Override
    public void execute(Frame frame) {
        JvmObject ref = frame.getOperandStack().popRef();
        if (ref == null) {
            throw new NullPointerException();
        }
    }
}
