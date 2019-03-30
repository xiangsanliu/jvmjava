package com.xiang.jvmjava.instruction.stack.dup;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slot;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:01
 * @comment Duplicate the top one or two operand stack values
 */

/*
bottom -> top
[...][c][b][a]____
          \____   |
               |  |
               V  V
[...][c][b][a][b][a]
*/

public class DUP2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(new Slot(slot2));
        stack.pushSlot(new Slot(slot1));
        stack.pushSlot(new Slot(slot2));
        stack.pushSlot(new Slot(slot1));
    }
}
