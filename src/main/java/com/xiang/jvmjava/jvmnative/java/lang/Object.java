package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 10:01
 * @comment
 */

public class Object {

    private static final java.lang.String CLASS_STR = "java/lang/Object";

    private static Function<Frame, Void> getClass = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        JvmObject jvmClass = self.getClazz().getJvmClass();
        frame.getOperandStack().pushRef(jvmClass);
        return null;
    };

    private static Function<Frame, Void> hashCode = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        frame.getOperandStack().pushInt(self.hashCode());
        return null;
    };

    private static Function<Frame, Void> clone = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        JvmClass cloneable = self.getClazz().getLoader().loadClass("java/lang/Cloneable");
        if (!self.getClazz().isImplements(cloneable)) {
            throw new Error("java.lang.CloneNotSupportedException");
        }
        frame.getOperandStack().pushRef(self.jvmClone());
        return null;
    };

    private static Function<Frame, Void> registerNatives = frame -> {
        Registry.register(CLASS_STR, "getClass", "()Ljava/lang/Class;", getClass);
        Registry.register(CLASS_STR, "hashCode", "()I", hashCode);
        Registry.register(CLASS_STR, "clone", "()Ljava/lang/Object;", clone);
        return null;
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", registerNatives);
    }


}