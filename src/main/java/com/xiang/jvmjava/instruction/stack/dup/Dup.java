package com.xiang.jvmjava.instruction.stack.dup;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slot;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:45
 * @comment Duplicate the top operand stack value
 */
/*
bottom -> top
[...][c][b][a]
             \_
               |
               V
[...][c][b][a][a]
*/

public class Dup extends NoOperandsInstruction {

    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
