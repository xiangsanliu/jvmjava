package com.xiang.jvmjava.instruction.constants;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.classfile.rtda.heap.ref.ClassRef;
import com.xiang.jvmjava.instruction.base.Index8Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/29 15:12
 * @comment
 */

public class LDC extends Index8Instruction {

    @Override
    public void execute(Frame frame) throws IOException {
        OperandStack stack = frame.getOperandStack();
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        Object val = constantPool.getConstant(this.index);
        if (val instanceof Integer) {
            stack.pushInt((Integer) val);
        } else if (val instanceof Float) {
            stack.pushFloat((Float) val);
        } else if (val instanceof String) {
            stack.pushRef(StringPool.getJvmString(frame.getMethod().getClazz().getLoader(), (String) val));
        } else if (val instanceof ClassRef) {
            throw new Error("todo");
        } else {
            throw new Error("todo");
        }
    }
}
