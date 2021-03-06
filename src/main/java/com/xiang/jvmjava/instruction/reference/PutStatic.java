package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.Slots;
import com.xiang.jvmjava.rtda.heap.member.Field;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.rtda.heap.ref.FieldRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

/**
 * @author 项三六
 * @time 2019/3/28 15:13
 * @comment
 */

public class PutStatic extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Method currentMethod = frame.getMethod();
        JvmClass currentClass = currentMethod.getClazz();
        JvmConstantPool constantPool = currentClass.getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();
        JvmClass clazz = field.getClazz();
        if (!clazz.isInitStarted()) {
            frame.revertNextPC();
            initClass(frame.getThread(), clazz);
            return;
        }
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (currentClass != clazz || !"<clinit>".equals(currentMethod.getName())) {
                throw new IllegalAccessError();
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = clazz.getStaticVars();
        OperandStack stack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                slots.setInt(slotId, stack.popInt());
                break;
            case 'F':
                slots.setFloat(slotId, stack.popFloat());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
                break;
            case 'L':
            case '[':
                slots.setRef(slotId, stack.popRef());
                break;
            default:
                throw new Error("todo");
        }
    }
}
