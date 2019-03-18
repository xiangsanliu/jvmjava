package com.xiang.jvmjava.instruction.math;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/3/18 20:33
 * @comment
 */

public class REM {

    public static class DRem extends NoOperandsInstruction {

        @Override
        public void Execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double v2 = stack.popDouble();
            double v1 = stack.popDouble();

        }
    }

}
