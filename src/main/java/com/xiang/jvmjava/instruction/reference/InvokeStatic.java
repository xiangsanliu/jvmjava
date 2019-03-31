package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.Method;
import com.xiang.jvmjava.classfile.rtda.heap.ref.MethodRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/31 20:58
 * @comment
 */

public class InvokeStatic extends Index16Instruction {

    @Override
    public void execute(Frame frame) throws IOException {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Method method = methodRef.resolvedMethod();
        if (!method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        invokeMethod(frame, method);
    }
}
