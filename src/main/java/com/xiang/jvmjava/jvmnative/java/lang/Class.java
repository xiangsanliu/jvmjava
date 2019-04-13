package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.*;
import com.xiang.jvmjava.classfile.rtda.Thread;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Field;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 10:18
 * @comment
 */

public class Class {

    private static final java.lang.String CLASS_STR = "java/lang/Class";

    private static final java.lang.String FIELDC_CONSTRUCTOR_DESCRIPTOR = "" +
            "(Ljava/lang/Class;" +
            "Ljava/lang/String;" +
            "Ljava/lang/Class;" +
            "II" +
            "Ljava/lang/String;" +
            "[B)V";

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
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        frame.getOperandStack().pushBoolean(clazz.isInterface());
        return null;
    };

    private static Function<Frame, Void> isPrimitive = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        frame.getOperandStack().pushBoolean(clazz.isPrimitive());
        return null;
    };

    private static Function<Frame, Void> getDeclaredFields0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject classObj = vars.getThis();
        boolean publicOnly = vars.getBoolean(1);
        JvmClass clazz = (JvmClass) classObj.getExtra();
        List<Field> fields = clazz.getPublicFields(publicOnly);
        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        JvmClass fieldClass = loader.loadClass("java/lang/reflect/Field");
        JvmObject fieldArr = fieldClass.getArrayClass().newArray(fields.size());
        frame.getOperandStack().pushRef(fieldArr);
        if (fields.size() > 0) {
            Thread thread = frame.getThread();
            JvmObject[] fieldObjs = fieldArr.getRefs();
            Method fieldConstructor = fieldClass.getConstructor(FIELDC_CONSTRUCTOR_DESCRIPTOR);
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                JvmObject fieldObj = fieldClass.newObject();
                fieldObj.setExtra(field);
                fieldObjs[i] = fieldObj;
                OperandStack ops = new OperandStack(8);
                ops.pushRef(fieldObj);                                        // this
                ops.pushRef(classObj);                                       // declaringClass
                ops.pushRef(StringPool.getJvmString(loader, field.getName()));
                ops.pushRef(field.getType().getJvmClass());
                ops.pushInt(field.getAccessFlags());
                ops.pushInt(field.getSlotId());
                ops.pushRef(null);
                ops.pushRef(null);
                Frame shimFrame = new Frame(thread, ops);
                thread.pushFrame(shimFrame);
                Instruction.invokeMethod(shimFrame, fieldConstructor);
            }
        }
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
        throw new Error("todo");
    };

    private static Function<Frame, Void> getModifiers = frame -> {
        throw new Error("todo");
    };

    private static Function<Frame, Void> getSuperclass = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        JvmClass superClass = clazz.getSuperClass();
        if (superClass != null) {
            frame.getOperandStack().pushRef(superClass.getJvmClass());
        } else {
            frame.getOperandStack().pushRef(null);
        }
        return null;

    };

    private static Function<Frame, Void> getInterfaces0 = frame -> {
        throw new Error("todo");

    };

    private static Function<Frame, Void> isArray = frame -> {
        throw new Error("todo");
    };

    private static Function<Frame, Void> getDeclaredMethods0 = frame -> {
        throw new Error("todo");
    };

    private static Function<Frame, Void> getComponentType = frame -> {
        throw new Error("todo");
    };

    private static Function<Frame, Void> isAssignableFrom = frame -> {
        throw new Error("todo");
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
