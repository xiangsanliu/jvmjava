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
 * @time 2019/4/2 17:09
 * @comment
 */

public class ANewArray extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        JvmClass elementClass = classRef.resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        JvmClass arrayClass = elementClass.getArrayClass();
        JvmObject array = arrayClass.newArray(count);
        stack.pushRef(array);
    }
}
