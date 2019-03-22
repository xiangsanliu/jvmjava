package com.xiang.jvmjava.instruction.control;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.base.BranchInstruction;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 15:46
 * @comment
 */

public class Goto extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, offset);
    }
}
