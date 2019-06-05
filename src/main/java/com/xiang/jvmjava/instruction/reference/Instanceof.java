package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.ref.ClassRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

/**
 * @author 项三六
 * @time 2019/3/28 16:05
 * @comment
 */

public class Instanceof extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        JvmObject ref = stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
            return;
        }
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        JvmClass clazz = classRef.resolvedClass();
        if (ref.isInstantOf(clazz)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }
    }
}
