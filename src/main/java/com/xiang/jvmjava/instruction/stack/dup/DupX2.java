package com.xiang.jvmjava.instruction.stack.dup;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slot;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:54
 * @comment Duplicate the top operand stack value and insert two or three values down
 */
/*
bottom -> top
[...][c][b][a]
       _____/
      |
      V
[...][a][c][b][a]
*/

public class DupX2 extends NoOperandsInstruction {

    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
