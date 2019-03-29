package com.xiang.jvmjava.instruction.constants;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.ref.MethodRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/29 16:11
 * @comment
 */

public class InvokeVirtual extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        if ("println".equals(methodRef.getName())) {
            OperandStack stack = frame.getOperandStack();
            switch (methodRef.getDescriptor()) {
                case "(Z)V":
                    System.out.println(stack.popInt() != 0);
                case "(C)V":
                    System.out.println(stack.popInt());
                case "(I)V":
                case "(B)V":
                case "(S)V":
                    System.out.println(stack.popInt());
                case "(F)V":
                    System.out.println(stack.popFloat());
                case "(J)V":
                    System.out.println(stack.popLong());
                case "(D)V":
                    System.out.println(stack.popDouble());
                default:
                    throw new Error("println: " + methodRef.getDescriptor());

            }
        }
    }
}
