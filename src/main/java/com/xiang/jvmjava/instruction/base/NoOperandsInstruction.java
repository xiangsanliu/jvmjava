package com.xiang.jvmjava.instruction.base;

import com.xiang.jvmjava.instruction.BytecodeReader;

/**
 * @author 项三六
 * @time 2019/3/17 19:31
 * @comment
 */

public abstract class NoOperandsInstruction extends Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // do nothing;
    }
}
