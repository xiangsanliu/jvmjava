package com.xiang.jvmjava.jvmnative.sun.misc;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/13 16:16
 * @comment
 */

public class Unsafe {

    private static final String CLASS_STR = "sun/misc/Unsafe";

    private static Function<Frame, Void> arrayBaseOffset = frame -> {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(0);
        return null;
    };

    private static Function<Frame, Void> arrayIndexScale = frame -> {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(1);
        return null;
    };

    private static Function<Frame, Void> addressSize = frame -> {
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(8);
        return null;
    };

    private static Function<Frame, Void> objectFieldOffset = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject field = vars.getRef(1);
        int offset = field.getIntVar("slot", "I");
        OperandStack stack = frame.getOperandStack();
        stack.pushLong(offset);
        return null;
    };

    private static Function<Frame, Void> compareAndSwapObject = frame -> {
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
        return null;
    };

    private static Function<Frame, Void> registerNatives = frame -> {
        Registry.register(CLASS_STR, "arrayBaseOffset", "(Ljava/lang/Class;)I", arrayBaseOffset);
        Registry.register(CLASS_STR, "arrayIndexScale", "(Ljava/lang/Class;)I", arrayIndexScale);
        Registry.register(CLASS_STR, "addressSize", "()I", addressSize);
        Registry.register(CLASS_STR, "objectFieldOffset", "(Ljava/lang/reflect/Field;)J", objectFieldOffset);
        Registry.register(CLASS_STR, "compareAndSwapObject", "(Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z", compareAndSwapObject);
//        Registry.register(CLASS_STR, "getIntVolatile", "(Ljava/lang/Object;J)I", getInt);
//        Registry.register(CLASS_STR, "compareAndSwapInt", "(Ljava/lang/Object;JII)Z", compareAndSwapInt);
//        Registry.register(CLASS_STR, "getObjectVolatile", "(Ljava/lang/Object;J)Ljava/lang/Object;", getObject);
//        Registry.register(CLASS_STR, "compareAndSwapLong", "(Ljava/lang/Object;JJJ)Z", compareAndSwapLong);

        return null;
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", registerNatives);
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
