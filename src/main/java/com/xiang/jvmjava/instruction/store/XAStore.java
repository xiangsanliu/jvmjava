package com.xiang.jvmjava.instruction.store;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;

/**
 * @author 项三六
 * @time 2019/4/3 10:51
 * @comment
 */

public class XAStore {

    public static class IAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            int[] ints = arrayRef.getInts();
            checkIndex(ints.length, index);
            ints[index] = val;
        }
    }

    public static class AAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            JvmObject ref = stack.popRef();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            JvmObject[] refs = arrayRef.getRefs();
            checkIndex(refs.length, index);
            refs[index] = ref;
        }
    }

    public static class BAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            byte[] bytes = arrayRef.getBytes();
            checkIndex(bytes.length, index);
            bytes[index] = (byte) val;
        }
    }

    public static class CAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            char[] chars = arrayRef.getChars();
            checkIndex(chars.length, index);
            chars[index] = (char) val;
        }
    }

    public static class DAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            double val = stack.popDouble();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            double[] doubles = arrayRef.getDoubles();
            checkIndex(doubles.length, index);
            doubles[index] = val;
        }
    }

    public static class FAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            float val = stack.popFloat();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            float[] floats = arrayRef.getFloats();
            checkIndex(floats.length, index);
            floats[index] = val;
        }
    }

    public static class LAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            long val = stack.popLong();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            long[] longs = arrayRef.getLongs();
            checkIndex(longs.length, index);
            longs[index] = val;
        }
    }

    public static class SAStore extends NoOperandsInstruction {

        @Override
        public void execute(Frame frame) {
            OperandStack stack = frame.getOperandStack();
            int val = stack.popInt();
            int index = stack.popInt();
            JvmObject arrayRef = stack.popRef();

            checkNoNull(arrayRef);
            short[] shorts = arrayRef.getShorts();
            checkIndex(shorts.length, index);
            shorts[index] = (short) val;
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
