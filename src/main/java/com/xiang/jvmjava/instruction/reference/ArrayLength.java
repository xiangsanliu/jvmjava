package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/4/3 10:30
 * @comment
 */

public class ArrayLength extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        JvmObject arrayRef = stack.popRef();
        if (arrayRef == null) {
            throw new NullPointerException();
        }
        stack.pushInt(arrayRef.getArrayLength());
    }
}
