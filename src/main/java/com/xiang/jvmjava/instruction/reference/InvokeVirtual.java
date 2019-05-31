package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
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
        JvmClass currentClass = frame.getMethod().getClazz();
        JvmConstantPool constantPool = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(this.index);
        Method resolvedMethod = methodRef.resolvedMethod();
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        JvmObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            if (methodRef.getName().equals("print")) {
                print(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
            if (methodRef.getName().equals("println")) {
                println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
            throw new NullPointerException(frame.getMethod().getName());
        }
        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSuperClassOf(currentClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                ref.getClazz() != currentClass &&
                !ref.getClazz().isSubClassOf(currentClass)) {
            if (!(ref.getClazz().isArray() && resolvedMethod.getName().equals("clone"))) {
                throw new IllegalAccessError();
            }
        }
        Method tobeInvoked = MethodRef.lookupMethodInClass(ref.getClazz(), methodRef.getName(), methodRef.getDescriptor());
        if (tobeInvoked == null || tobeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        invokeMethod(frame, tobeInvoked);
    }

    private void print(OperandStack stack, String descriptor) {
        switch (descriptor) {
            case "(Z)V":
                System.err.print(stack.popInt() != 0);
                break;
            case "(C)V":
                System.err.printf("%c", stack.popInt());
                break;
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.err.print(stack.popInt());
                break;
            case "(F)V":
                System.err.print(stack.popFloat());
                break;
            case "(J)V":
                System.err.print(stack.popLong());
                break;
            case "(D)V":
                System.err.print(stack.popDouble());
                break;
            case "(Ljava/lang/String;)V":
                JvmObject jvmStr = stack.popRef();
                System.err.print(StringPool.jvmStrToString(jvmStr));
                break;
            default:

        }
        stack.popRef();
    }

    private void println(OperandStack stack, String descriptor) {
        print(stack, descriptor);
        System.err.println();
    }

}