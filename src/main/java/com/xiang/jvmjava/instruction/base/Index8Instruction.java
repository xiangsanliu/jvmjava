package com.xiang.jvmjava.instruction.base;

import com.xiang.jvmjava.instruction.BytecodeReader;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/17 19:38
 * @comment
 */

public abstract class Index8Instruction extends Instruction {

    protected int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint8();
    }

}
