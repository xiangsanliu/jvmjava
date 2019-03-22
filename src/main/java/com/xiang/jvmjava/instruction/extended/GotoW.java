package com.xiang.jvmjava.instruction.extended;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 16:10
 * @comment
 */

public class GotoW extends Instruction {

    private int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt32();
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }
}
