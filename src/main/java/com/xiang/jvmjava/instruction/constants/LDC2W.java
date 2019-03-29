package com.xiang.jvmjava.instruction.constants;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

/**
 * @author 项三六
 * @time 2019/3/29 15:17
 * @comment
 */

public class LDC2W extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Object val = constantPool.getConstant(this.index);
        if (val instanceof Long) {
            stack.pushLong((Long) val);
        } else if (val instanceof Double) {
            stack.pushDouble((Double) val);
        } else {
            throw new ClassFormatError();
        }
    }
}
