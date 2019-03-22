package com.xiang.jvmjava.instruction.constants;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/17 20:26
 * @comment
 */

public class NOP extends NoOperandsInstruction {


    @Override
    public void execute(Frame frame) {
        // do nothing.
    }
}
