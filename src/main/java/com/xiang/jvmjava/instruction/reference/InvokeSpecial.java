package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.Method;
import com.xiang.jvmjava.classfile.rtda.heap.ref.MethodRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/29 16:10
 * @comment
 */

public class InvokeSpecial extends Index16Instruction {

    @Override
    public void execute(Frame frame) throws IOException {
        JvmClass currentClass = frame.getMethod().getClazz();
        JvmConstantPool constantPool = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        JvmClass resolvedClass = methodRef.resolvedClass();
        Method resolvedMethod = methodRef.resolvedMethod();
        // 构造方法不能由其他类调用
        if (resolvedClass.getName().equals("<init>") && resolvedMethod.getClazz() != resolvedClass) {
            throw new NoSuchMethodError();
        }
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        // 弹出this引用
        JvmObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new NullPointerException();
        }
        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSuperClassOf(currentClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                ref.getClazz() != currentClass &&
                !ref.getClazz().isSubClassOf(currentClass)) {
            throw new IllegalAccessError();
        }
        Method toBeInvoked = resolvedMethod;
        if (currentClass.isSuper() &&
                resolvedClass.isSubClassOf(currentClass) &&
                !resolvedMethod.getName().equals("<init>")) {
            toBeInvoked = MethodRef.lookupMethodInClass(currentClass.getSuperClass(), methodRef.getName(), methodRef.getDescriptor());
        }
        if (toBeInvoked == null || toBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        invokeMethod(frame, toBeInvoked);
    }
}
