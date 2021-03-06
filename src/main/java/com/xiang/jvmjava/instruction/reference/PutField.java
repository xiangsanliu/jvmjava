package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.heap.*;
import com.xiang.jvmjava.rtda.heap.member.Field;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.rtda.heap.ref.FieldRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

/**
 * @author 项三六
 * @time 2019/3/28 15:53
 * @comment
 */

public class PutField extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        JvmClass currentClass = currentMethod.getClazz();
        JvmConstantPool constantPool = currentClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();
        JvmClass clazz = field.getClazz();
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (currentClass != field.getClazz() || !"<init>".equals(currentMethod.getName())) {
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();
        JvmObject ref;
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                int i = stack.popInt();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.getFields().setInt(slotId, i);
                break;
            case 'F':
                float f = stack.popFloat();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.getFields().setFloat(slotId, f);
                break;
            case 'J':
                long l = stack.popLong();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.getFields().setLong(slotId, l);
                break;
            case 'D':
                double d = stack.popDouble();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.getFields().setDouble(slotId, d);
                break;
            case 'L':
            case '[':
                JvmObject r = stack.popRef();
                ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.getFields().setRef(slotId, r);
                break;
            default:
                throw new Error("todo");
        }
    }
}
