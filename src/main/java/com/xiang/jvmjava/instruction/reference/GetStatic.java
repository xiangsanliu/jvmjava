package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.Slots;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.rtda.heap.member.Field;
import com.xiang.jvmjava.rtda.heap.ref.FieldRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

/**
 * @author 项三六
 * @time 2019/3/28 15:49
 * @comment
 */

public class GetStatic extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
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
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                throw new Error("todo");
        }
    }
}
