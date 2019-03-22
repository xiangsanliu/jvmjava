package com.xiang.jvmjava.instruction.control;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 15:47
 * @comment
 */

public class LookupSwitch extends Instruction {

    private int defaultOffset;

    private int nPairs;

    private int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        this.defaultOffset = reader.readInt32();
        this.nPairs = reader.readInt32();
        this.matchOffsets = reader.readInt32s(nPairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.getOperandStack().popInt();
        for (int i = 0; i < nPairs * 2; i += 2) {
            if (key == matchOffsets[i]) {
                int offset = matchOffsets[i + 1];
                Instruction.branch(frame, offset);
                return;
            }
        }
        Instruction.branch(frame, defaultOffset);
    }
}
