package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:21
 * @comment Increment local variable by constant
 */

public class IInc extends Instruction {

    private int index;

    private int con;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint8();
        this.con = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {
        Slots localVars = frame.getLocalVars();
        int val = localVars.getInt(this.index);
        val += this.con;
        localVars.setInt(this.index, val);
    }

    public IInc setIndexAndCon(int index, int con) {
        this.index = index;
        this.con = con;
        return this;
    }
}
