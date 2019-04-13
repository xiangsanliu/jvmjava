package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 10:18
 * @comment
 */

public class Class {

    private static final java.lang.String CLASS_STR = "java/lang/Class";

    private static Function<Frame, Void> getPrimitiveClass = frame -> {
        JvmObject nameObj = frame.getLocalVars().getRef(0);
        java.lang.String name = StringPool.jvmStrToString(nameObj);
        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        JvmObject jvmClass = loader.loadClass(name).getJvmClass();
        frame.getOperandStack().pushRef(jvmClass);
        return null;
    };

    private static Function<Frame, Void> getName0 = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        java.lang.String name = clazz.getClassName();
        JvmObject nameObj = StringPool.getJvmString(clazz.getLoader(), name);
        frame.getOperandStack().pushRef(nameObj);
        return null;
    };

    private static Function<Frame, Void> desiredAssertionStatus0 = frame -> {
        frame.getOperandStack().pushBoolean(false);
        return null;
    };

    private static Function<Frame, Void> isInterface = frame -> {
        return null;
    };

    private static Function<Frame, Void> isPrimitive = frame -> {
        return null;
    };

    private static Function<Frame, Void> getDeclaredFields0 = frame -> {
        return null;
    };

    private static Function<Frame, Void> forName0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject nameStr = vars.getRef(0);
        boolean initialize = vars.getBoolean(1);
        java.lang.String name = StringPool.jvmStrToString(nameStr);
        name = StringUtils.replaceChars(name, ".", "/");
        JvmClass clazz = frame.getMethod().getClazz().getLoader().loadClass(name);
        JvmObject jvmClass = clazz.getJvmClass();
        if (initialize && clazz.isInitStarted()) {
            frame.setNextPC(frame.getThread().getPC());
            Instruction.initClass(frame.getThread(), clazz);
        } else {
            frame.getOperandStack().pushRef(jvmClass);
        }
        return null;
    };

    private static Function<Frame, Void> getDeclaredConstructors0 = frame -> {
        return null;
    };

    private static Function<Frame, Void> getModifiers = frame -> {
        return null;
    };

    private static Function<Frame, Void> getSuperclass = frame -> {
        return null;
    };

    private static Function<Frame, Void> getInterfaces0 = frame -> {
        return null;
    };

    private static Function<Frame, Void> isArray = frame -> {
        return null;
    };

    private static Function<Frame, Void> getDeclaredMethods0 = frame -> {
        return null;
    };

    private static Function<Frame, Void> getComponentType = frame -> {
        return null;
    };

    private static Function<Frame, Void> isAssignableFrom = frame -> {
        return null;
    };

    private static Function<Frame, Void> registerNatives = frame -> {
        Registry.register(CLASS_STR, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", getPrimitiveClass);
        Registry.register(CLASS_STR, "getName0", "()Ljava/lang/String;", getName0);
        Registry.register(CLASS_STR, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", desiredAssertionStatus0);
        Registry.register(CLASS_STR, "isInterface", "()Z", isInterface);
        Registry.register(CLASS_STR, "isPrimitive", "()Z", isPrimitive);
        Registry.register(CLASS_STR, "getDeclaredFields0", "(Z)[Ljava/lang/reflect/Field;", getDeclaredFields0);
        Registry.register(CLASS_STR, "forName0", "(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;", forName0);
        Registry.register(CLASS_STR, "getDeclaredConstructors0", "(Z)[Ljava/lang/reflect/Constructor;", getDeclaredConstructors0);
        Registry.register(CLASS_STR, "getModifiers", "()I", getModifiers);
        Registry.register(CLASS_STR, "getSuperclass", "()Ljava/lang/Class;", getSuperclass);
        Registry.register(CLASS_STR, "getInterfaces0", "()[Ljava/lang/Class;", getInterfaces0);
        Registry.register(CLASS_STR, "isArray", "()Z", isArray);
        Registry.register(CLASS_STR, "getDeclaredMethods0", "(Z)[Ljava/lang/reflect/Method;", getDeclaredMethods0);
        Registry.register(CLASS_STR, "getComponentType", "()Ljava/lang/Class;", getComponentType);
        Registry.register(CLASS_STR, "isAssignableFrom", "(Ljava/lang/Class;)Z", isAssignableFrom);
        return null;
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", registerNatives);
    }

}
