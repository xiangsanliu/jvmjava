package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/4/2 16:59
 * @comment
 */

public class NewArray extends Instruction {

    private int aType;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.aType = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) throws IOException {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        JvmClass arrClass = getPrimitiveArrayClass(frame.getMethod().getClazz().getLoader());
        JvmObject array = arrClass.newArray((int) Integer.toUnsignedLong(count));
        stack.pushRef(array);
    }

    private JvmClass getPrimitiveArrayClass(ClassLoader loader) throws IOException {
        switch (aType) {
            case 4:
                return loader.loadClass("[Z");
            case 8:
                return loader.loadClass("[B");
            case 5:
                return loader.loadClass("[C");
            case 9:
                return loader.loadClass("[S");
            case 10:
                return loader.loadClass("[I");
            case 11:
                return loader.loadClass("[J");
            case 6:
                return loader.loadClass("[F");
            case 7:
                return loader.loadClass("[D");
            default:
                throw new Error("Invalid AType!");
        }
    }

}
