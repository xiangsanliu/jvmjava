package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.rtda.heap.ref.InterfaceMethodRef;
import com.xiang.jvmjava.rtda.heap.ref.MethodRef;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/4/1 14:44
 * @comment
 */

public class InvokeInterface extends Instruction {

    private int index;

//    private int count; // could be counted by method descriptor;
//    private int zero;  // must be 0

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint16();
        reader.readUint8();
        reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        InterfaceMethodRef methodRef = (InterfaceMethodRef) constantPool.getConstant(this.index);
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();
        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new IncompatibleClassChangeError();
        }
        JvmObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new NullPointerException();
        }
        if (!ref.getClazz().isImplements(methodRef.resolvedClass())) {
            throw new IncompatibleClassChangeError();
        }

        Method tobeInvoked = MethodRef.lookupMethodInClass(ref.getClazz(), methodRef.getName(), methodRef.getDescriptor());
        if (tobeInvoked == null || tobeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        if (!tobeInvoked.isPublic()) {
            throw new IllegalAccessError();
        }
        invokeMethod(frame, tobeInvoked);
    }
}
