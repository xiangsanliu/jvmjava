package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.classfile.rtda.heap.ref.MethodRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/31 20:58
 * @comment 调用静态方法
 */

public class InvokeStatic extends Index16Instruction {

    @Override
    public void execute(Frame frame) throws IOException {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Method resolvedMethod = methodRef.resolvedMethod();
        if (!resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        JvmClass clazz = resolvedMethod.getClazz();
        if (!clazz.isInitStarted()) {
            frame.revertNextPC();
            initClass(frame.getThread(), clazz);
            return;
        }
        invokeMethod(frame, resolvedMethod);
    }
}
