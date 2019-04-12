package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 10:18
 * @comment
 */

public class Class {

    private static final String classStr = "java/lang/Class";

    private static Function<Frame, Void> getPrimitiveClass = frame -> {
        JvmObject nameObj = frame.getLocalVars().getRef(0);
        String name = StringPool.jvmStrToString(nameObj);
        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        JvmObject jvmClass = loader.loadClass(name).getJvmClass();
        frame.getOperandStack().pushRef(jvmClass);
        return null;
    };

    private static Function<Frame, Void> getName0 = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        String name = clazz.getClassName();
        JvmObject nameObj = StringPool.getJvmString(clazz.getLoader(), name);
        frame.getOperandStack().pushRef(nameObj);
        return null;
    };

    private static Function<Frame, Void> desiredAssertionStatus0 = frame -> {
        frame.getOperandStack().pushBoolean(false);
        return null;
    };

    private static Function<Frame, Void> registerNatives = frame -> {
        Registry.register(classStr, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", getPrimitiveClass);
        Registry.register(classStr, "getName0", "()Ljava/lang/String;", getName0);
        Registry.register(classStr, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", desiredAssertionStatus0);
        return null;
    };

    public static void init() {
        Registry.register(classStr, "registerNatives", "()V", registerNatives);
    }


}
