package com.xiang.jvmjava.instruction.stack.dup;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.Slot;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 19:49
 * @comment Duplicate the top operand stack value and insert two values down
 */

/*
bottom -> top
[...][c][b][a]
          __/
         |
         V
[...][c][a][b][a]
*/

public class DupX1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(new Slot(slot1));
        stack.pushSlot(slot2);
        stack.pushSlot(new Slot(slot1));
    }
}
