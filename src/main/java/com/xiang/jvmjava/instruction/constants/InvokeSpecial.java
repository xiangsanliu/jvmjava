package com.xiang.jvmjava.instruction.constants;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/29 16:10
 * @comment
 */

public class InvokeSpecial extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popRef();
    }
}
