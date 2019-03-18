package com.xiang.jvmjava.instruction.base;

import com.xiang.jvmjava.instruction.BytecodeReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/17 19:33
 * @comment
 */

public abstract class BranchInstruction extends Instruction {

    @Getter
    @Setter
    int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.offset = reader.readInt16();
    }
}
