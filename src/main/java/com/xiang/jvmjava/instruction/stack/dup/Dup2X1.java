package com.xiang.jvmjava.instruction.stack.dup;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.Slot;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:03
 * @comment Duplicate the top one or two operand stack values and insert two or three values down
 */
/*
bottom -> top
[...][c][b][a]
       _/ __/
      |  |
      V  V
[...][b][a][c][b][a]
*/
public class Dup2X1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        stack.pushSlot(new Slot(slot2));
        stack.pushSlot(new Slot(slot1));
        stack.pushSlot(slot3);
        stack.pushSlot(new Slot(slot2));
        stack.pushSlot(new Slot(slot1));
    }
}
