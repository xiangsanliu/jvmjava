package com.xiang.jvmjava.instruction.control;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 15:50
 * @comment
 */

public class TableSwitch extends Instruction {

    private int defaultOffset;

    private int low;

    private int high;

    private int[] jumpOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.low = reader.readInt32();
        this.high = reader.readInt32();
        int count = high - low + 1;
        jumpOffsets = reader.readInt32s(count);
    }

    @Override
    public void execute(Frame frame) {
        int index = frame.getOperandStack().popInt();
        int offset;
        if (index >= low && index <= high) {
            offset = jumpOffsets[index - low];
        } else {
            offset = defaultOffset;
        }
        Instruction.branch(frame, offset);
    }
}
