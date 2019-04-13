package com.xiang.jvmjava.jvmnative.sun.reflect;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/13 16:26
 * @comment
 */

public class Reflection {

    private static final String CLASS_STR = "sun/reflect/Reflection";

    private static Function<Frame, Void> getCallerClass = frame -> {
        Frame callerFrame = frame.getThread().getFrames(0).get(2);  // todo
        JvmObject callerClass = callerFrame.getMethod().getClazz().getJvmClass();
        frame.getOperandStack().pushRef(callerClass);
        return null;
    };

    private static Function<Frame, Void> getClassAccessFlags = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject type = vars.getRef(0);
        JvmClass clazz = (JvmClass) type.getExtra();
        int flags = clazz.getAccessFlags();
        OperandStack stack = frame.getOperandStack();
        stack.pushInt(flags);
        return null;
    };

    public static void registerNatives() {
        Registry.register("sun/reflect/Reflection", "getCallerClass", "()Ljava/lang/Class;", getCallerClass);
        Registry.register("sun/reflect/Reflection", "getClassAccessFlags", "(Ljava/lang/Class;)I", getClassAccessFlags);
    }


}
