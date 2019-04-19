package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.Thread;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Field;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;
import com.xiang.jvmjava.util.Function;
import com.xiang.jvmjava.util.JvmClassHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author 项三六
 * @time 2019/4/12 10:18
 * @comment
 */

public class Class {

    private static final java.lang.String CLASS_STR = "java/lang/Class";

    private static final java.lang.String CONSTRUCTOR_CONSTRUCTOR_DESCRIPTOR = "" +
            "(Ljava/lang/Class;" +
            "[Ljava/lang/Class;" +
            "[Ljava/lang/Class;" +
            "II" +
            "Ljava/lang/String;" +
            "[B[B)V";
    private static final java.lang.String FIELDC_CONSTRUCTOR_DESCRIPTOR =
            "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;IILjava/lang/String;[B)V";

    private static Function<Frame> getPrimitiveClass = frame -> {
        JvmObject nameObj = frame.getLocalVars().getRef(0);
        java.lang.String name = StringPool.jvmStrToString(nameObj);
        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        JvmObject jvmClass = loader.loadClass(name).getJvmClass();
        frame.getOperandStack().pushRef(jvmClass);

    };

    private static Function<Frame> getName0 = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        java.lang.String name = clazz.getClassName();
        JvmObject nameObj = StringPool.getJvmString(clazz.getLoader(), name);
        frame.getOperandStack().pushRef(nameObj);

    };

    private static Function<Frame> desiredAssertionStatus0 = frame -> frame.getOperandStack().pushBoolean(false);

    private static Function<Frame> isInterface = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        frame.getOperandStack().pushBoolean(clazz.isInterface());

    };

    private static Function<Frame> isPrimitive = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        frame.getOperandStack().pushBoolean(clazz.isPrimitive());

    };

    private static Function<Frame> getDeclaredFields0 = frame -> {
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
                ops.pushRef(JvmClassHelper.getSignature(loader, field.getSignature()));
                ops.pushRef(JvmClassHelper.toJvmByteArray(loader, field.getAnnotationData()));
                Frame shimFrame = new Frame(thread, ops);
                thread.pushFrame(shimFrame);
                Instruction.invokeMethod(shimFrame, fieldConstructor);
            }
        }

    };

    private static Function<Frame> forName0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject nameStr = vars.getRef(0);
        boolean initialize = vars.getBoolean(1);
        java.lang.String name = StringPool.jvmStrToString(nameStr);
        name = StringUtils.replaceChars(name, ".", "/");
        JvmClass clazz = frame.getMethod().getClazz().getLoader().loadClass(name);
        if (initialize && !clazz.isInitStarted()) {
            frame.revertNextPC();
            Instruction.initClass(frame.getThread(), clazz);
        } else {
            frame.getOperandStack().pushRef(clazz.getJvmClass());
        }

    };

    private static Function<Frame> getDeclaredConstructors0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject classObj = vars.getThis();
        boolean publicOnly = vars.getBoolean(1);

        JvmClass clazz = (JvmClass) classObj.getExtra();
        List<Method> constructors = clazz.getConstructors(publicOnly);
        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        JvmClass constructorClass = loader.loadClass("java/lang/reflect/Constructor");

        JvmObject constructorArr = constructorClass.getArrayClass().newArray(constructors.size());
        frame.getOperandStack().pushRef(constructorArr);

        if (constructors.size() > 0) {
            Thread thread = frame.getThread();
            JvmObject[] constructorObjs = constructorArr.getRefs();
            Method constructorMethod = constructorClass.getConstructor(CONSTRUCTOR_CONSTRUCTOR_DESCRIPTOR);
            for (int i = 0; i < constructors.size(); i++) {
                Method constructor = constructors.get(i);

                JvmObject constructorObj = constructorClass.newObject();
                constructorObj.setExtra(constructor);
                constructorObjs[i] = constructorObj;
                OperandStack ops = new OperandStack(9);
                ops.pushRef(constructorObj);                                        // this
                ops.pushRef(classObj);                                       // declaringClass
                ops.pushRef(JvmClassHelper.toClassArray(loader, constructor.getParameterTypes()));
                ops.pushRef(JvmClassHelper.toClassArray(loader, constructor.getExceptionTypes()));
                ops.pushInt(constructor.getAccessFlags());
                ops.pushInt(0);
                ops.pushRef(JvmClassHelper.getSignature(loader, constructor.getSignature()));
                ops.pushRef(JvmClassHelper.toJvmByteArray(loader, constructor.getAnnotationData()));
                ops.pushRef(JvmClassHelper.toJvmByteArray(loader, constructor.getParameterAnnotationData()));
                Frame shimFrame = new Frame(thread, ops);
                thread.pushFrame(shimFrame);
                Instruction.invokeMethod(shimFrame, constructorMethod);
            }
        }
    };

    private static Function<Frame> getModifiers = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        int modifers = clazz.getAccessFlags();
        frame.getOperandStack().pushInt(modifers);

    };

    private static Function<Frame> getSuperclass = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmClass clazz = (JvmClass) self.getExtra();
        JvmClass superClass = clazz.getSuperClass();
        if (superClass != null) {
            frame.getOperandStack().pushRef(superClass.getJvmClass());
        } else {
            frame.getOperandStack().pushRef(null);
        }


    };

    private static Function<Frame> getInterfaces0 = frame -> {
        throw new Error("todo");

    };

    private static Function<Frame> isArray = frame -> {
        throw new Error("todo");
    };

    private static Function<Frame> getDeclaredMethods0 = frame -> {
        throw new Error("todo");
    };

    private static Function<Frame> getComponentType = frame -> {
        throw new Error("todo");
    };

    private static Function<Frame> isAssignableFrom = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject self = vars.getThis();
        JvmObject obj = vars.getRef(1);
        JvmClass thisClass = (JvmClass) self.getExtra();
        JvmClass objClass = (JvmClass) obj.getExtra();
        frame.getOperandStack().pushBoolean(thisClass.isAssignableFrom(objClass));
    };

    private static Function<Frame> registerNatives = frame -> {
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
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", registerNatives);
    }

}
