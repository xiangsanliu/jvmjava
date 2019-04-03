package com.xiang.jvmjava.instruction.load;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/4/3 10:34
 * @comment
 */

public class XALoad {

    public static class AALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            JvmObject[] refs = arrayRef.getRefs();
            checkIndex(refs.length, index);
            stack.pushRef(refs[index]);
        }
    }

    public static class BALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            byte[] bytes = arrayRef.getBytes();
            checkIndex(bytes.length, index);
            stack.pushInt(bytes[index]);
        }
    }

    public static class CALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            char[] chars = arrayRef.getChars();
            checkIndex(chars.length, index);
            stack.pushInt(chars[index]);
        }
    }

    public static class DALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            double[] doubles = arrayRef.getDoubles();
            checkIndex(doubles.length, index);
            stack.pushDouble(doubles[index]);
        }
    }

    public static class FALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            float[] floats = arrayRef.getFloats();
            checkIndex(floats.length, index);
            stack.pushFloat(floats[index]);
        }
    }

    public static class IALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            int[] ints = arrayRef.getInts();
            checkIndex(ints.length, index);
            stack.pushInt(ints[index]);
        }
    }

    public static class LALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            long[] longs = arrayRef.getLongs();
            checkIndex(longs.length, index);
            stack.pushLong(longs[index]);
        }
    }

    public static class SALoad extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();
            checkNoNull(arrayRef);
            short[] shorts = arrayRef.getShorts();
            checkIndex(shorts.length, index);
            stack.pushInt(shorts[index]);
        }
    }


    private static void checkNoNull(JvmObject object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    private static void checkIndex(int len, int index) {
        if (index < 0 || index >= len) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}
