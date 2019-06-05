package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.ref.ClassRef;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author 项三六
 * @time 2019/4/3 11:00
 * @comment
 */

public class MultiANewArray extends Instruction {

    private int index;

    private int dimensions;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint16();
        this.dimensions = reader.readUint8();
    }

    @Override
    public void execute(Frame frame) {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        JvmClass arrayClass = classRef.resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int[] counts = popAndCheckCounts(stack, this.dimensions);
        JvmObject array = newMultiArray(counts, arrayClass);
        stack.pushRef(array);

    }

    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] counts = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            counts[i] = stack.popInt();
            if (counts[i] < 0) {
                throw new NegativeArraySizeException();
            }
        }
        return counts;
    }

    private JvmObject newMultiArray(int[] counts, JvmClass arrayClass) {
        int count = (int) Integer.toUnsignedLong(counts[0]);
        JvmObject array = arrayClass.newArray(count);
        if (counts.length > 1) {
            JvmObject[] refs = array.getRefs();
            for (int i = 0; i < refs.length; i++) {
                refs[i] = newMultiArray(ArrayUtils.subarray(counts, 1, counts.length), arrayClass.getElementClass());
            }
        }
        return array;
    }

}
