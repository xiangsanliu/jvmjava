package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.Field;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.ref.FieldRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/28 15:59
 * @comment
 */

public class GetField extends Index16Instruction {

    @Override
    public void execute(Frame frame) throws IOException {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        FieldRef fieldRef = (FieldRef) constantPool.getConstant(this.index);
        Field field = fieldRef.resolvedField();
        // todo: init class
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        OperandStack stack = frame.getOperandStack();
        JvmObject ref = stack.popRef();

        if (ref == null) {
            throw new NullPointerException();
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        Slots slots = ref.getFields();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.pushInt(slots.getInt(slotId));
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
            case 'J':
                stack.pushLong(slots.getLong(slotId));
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
            default:
                // todo
        }
    }
}
