package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.ref.MethodRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

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
                    break;
                case "(C)V":
                    System.out.println(String.format("%c", stack.popInt()));
                    break;
                case "(I)V":
                case "(B)V":
                case "(S)V":
                    System.out.println(stack.popInt());
                    break;
                case "(F)V":
                    System.out.println(stack.popFloat());
                    break;
                case "(J)V":
                    System.out.println(stack.popLong());
                    break;
                case "(D)V":
                    System.out.println(stack.popDouble());
                    break;
                default:
                    throw new Error("println: " + methodRef.getDescriptor());

            }
            stack.popRef();
        }
    }
}
