package com.xiang.jvmjava.jvmnative.sun.misc;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.Slots;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;
import com.xiang.jvmjava.util.Function;

import java.lang.reflect.Field;

/**
 * @author 项三六
 * @time 2019/4/13 16:16
 * @comment
 */

public class Unsafe {

    private static final String CLASS_STR = "sun/misc/Unsafe";

    private static Function<Frame> arrayBaseOffset = frame -> {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(0);

    };

    private static Function<Frame> arrayIndexScale = frame -> {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(1);

    };

    private static Function<Frame> addressSize = frame -> {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(8);

    };

    private static Function<Frame> objectFieldOffset = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject field = vars.getRef(1);
        int offset = field.getIntVar("slot", "I");
        OperandStack stack = frame.getOperandStack();
        stack.pushLong(offset);

    };

    private static Function<Frame> compareAndSwapObject = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject obj = vars.getRef(1);
        Object fields = obj.getData();
        long offset = vars.getLong(2);
        JvmObject expected = vars.getRef(4);
        JvmObject newVal = vars.getRef(5);
        if (fields instanceof Slots) {
            frame.getOperandStack().pushBoolean(casObj((Slots) fields, offset, expected, newVal));
        } else if (fields instanceof JvmObject[]) {
            frame.getOperandStack().pushBoolean(casArr((JvmObject[]) fields, offset, expected, newVal));
        } else {
            throw new Error("todo: compareAndSwapObject!");
        }

    };

    private static Function<Frame> getIntVolatile = frame -> {
        Slots vars = frame.getLocalVars();
        Object fields = vars.getRef(1).getData();
        long offset = vars.getLong(2);
        if (fields instanceof Slots) {
            frame.getOperandStack().pushInt(((Slots) fields).getInt((int) offset));
        } else if (fields instanceof int[]) {
            frame.getOperandStack().pushInt(((int[]) fields)[(int) offset]);
        } else {
            throw new Error("getIntVolatile!");
        }

    };

    private static Function<Frame> compareAndSwapInt = frame -> {
        Slots vars = frame.getLocalVars();
        Object fields = vars.getRef(1).getData();
        long offset = vars.getLong(2);
        int expected = vars.getInt(4);
        int newVal = vars.getInt(5);
        if (fields instanceof Slots) {
            int oldVal = ((Slots) fields).getInt((int) offset);
            if (oldVal == expected) {
                ((Slots) fields).setInt((int) offset, newVal);
                frame.getOperandStack().pushBoolean(true);
            } else {
                frame.getOperandStack().pushBoolean(false);
            }
        } else if (fields instanceof int[]) {
            int[] ints = (int[]) fields;
            int oldVal = ints[(int) offset];
            if (oldVal == expected) {
                ints[(int) offset] = newVal;
                frame.getOperandStack().pushBoolean(true);
            } else {
                frame.getOperandStack().pushBoolean(false);
            }
        } else {
            throw new Error("compareAndSwapInt");
        }

    };

    private static Function<Frame> getObjectVolatile = frame -> {
        throw new Error("todo");
//        
    };

    private static Function<Frame> allocateMemory = frame -> {
        Slots vars = frame.getLocalVars();
        try {
            Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            sun.misc.Unsafe unsafe = (sun.misc.Unsafe) field.get(null);

            frame.getOperandStack().pushLong(unsafe.allocateMemory(vars.getLong(1)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    };

    private static Function<Frame> registerNatives = frame -> {
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", registerNatives);
        Registry.register(CLASS_STR, "arrayBaseOffset", "(Ljava/lang/Class;)I", arrayBaseOffset);
        Registry.register(CLASS_STR, "arrayIndexScale", "(Ljava/lang/Class;)I", arrayIndexScale);
        Registry.register(CLASS_STR, "addressSize", "()I", addressSize);
        Registry.register(CLASS_STR, "objectFieldOffset", "(Ljava/lang/reflect/Field;)J", objectFieldOffset);
        Registry.register(CLASS_STR, "compareAndSwapObject", "(Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z", compareAndSwapObject);
        Registry.register(CLASS_STR, "getIntVolatile", "(Ljava/lang/Object;J)I", getIntVolatile);
        Registry.register(CLASS_STR, "compareAndSwapInt", "(Ljava/lang/Object;JII)Z", compareAndSwapInt);
        Registry.register(CLASS_STR, "getObjectVolatile", "(Ljava/lang/Object;J)Ljava/lang/Object;", getObjectVolatile);
        Registry.register(CLASS_STR, "allocateMemory", "(J)J", allocateMemory);

        Registry.register(CLASS_STR, "putLong", "(JJ)V", frame -> {
            long address = frame.getLocalVars().getLong(1);
            long x = frame.getLocalVars().getLong(3);
            try {
                Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                sun.misc.Unsafe unsafe = (sun.misc.Unsafe) field.get(null);
                unsafe.putLong(address, x);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        });

        Registry.register(CLASS_STR, "getByte", "(J)B", frame -> {
            long address = frame.getLocalVars().getLong(1);
            try {
                Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                sun.misc.Unsafe unsafe = (sun.misc.Unsafe) field.get(null);
                frame.getOperandStack().pushInt(unsafe.getByte(address));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        Registry.register(CLASS_STR, "freeMemory", "(J)V", frame -> {
            long address = frame.getLocalVars().getLong(1);
            try {
                Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                sun.misc.Unsafe unsafe = (sun.misc.Unsafe) field.get(null);
                unsafe.freeMemory(address);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }

    private static boolean casObj(Slots fields, long offset, JvmObject expected, JvmObject newVal) {
        JvmObject current = fields.getRef((int) offset);
        if (current == expected) {
            fields.setRef((int) offset, newVal);
            return true;
        } else {
            return false;
        }
    }

    private static boolean casArr(JvmObject[] objs, long offset, JvmObject expected, JvmObject newVal) {
        JvmObject current = objs[(int) offset];
        if (current == expected) {
            objs[(int) offset] = newVal;
            return true;
        } else {
            return false;
        }
    }

}
