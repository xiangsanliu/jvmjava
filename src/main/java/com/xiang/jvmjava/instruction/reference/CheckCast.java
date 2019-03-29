package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.ref.ClassRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/28 16:12
 * @comment
 */

public class CheckCast extends Index16Instruction {

    @Override
    public void execute(Frame frame) throws IOException {
        OperandStack stack = frame.getOperandStack();
        JvmObject ref = stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
            return;
        }
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        JvmClass clazz = classRef.resolvedClass();
        if (!ref.isInstantOf(clazz)) {
            throw new ClassCastException();
        }
    }
}
