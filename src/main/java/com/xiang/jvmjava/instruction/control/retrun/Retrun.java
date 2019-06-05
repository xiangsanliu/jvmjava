package com.xiang.jvmjava.instruction.control.retrun;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/31 20:42
 * @comment
 */

public class Retrun extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}
