package com.xiang.jvmjava.jvmnative.sun.reflect;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;

import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/16 20:49
 * @comment
 */

public class NativeConstructorAccessorImpl {

    private static Function<Frame> newInstance0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject constructorObj = vars.getRef(0);
        JvmObject argArrObj = vars.getRef(1);
        Method constructor = getConstructor(constructorObj);
        JvmClass clazz = constructor.getClazz();
        if (!clazz.isInitStarted()) {
            frame.revertNextPC();
            Instruction.initClass(frame.getThread(), clazz);
        }
        JvmObject object = clazz.newObject();
        frame.getOperandStack().pushRef(object);
        OperandStack ops = convertArgs(object, constructor);
        Frame shimFrame = new Frame(frame.getThread(), ops);
        frame.getThread().pushFrame(shimFrame);
        Instruction.invokeMethod(shimFrame, constructor);

    };

    public static void registerNatives() {
        Registry.register("sun/reflect/NativeConstructorAccessorImpl", "newInstance0", "(Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Ljava/lang/Object;", newInstance0);
    }

    private static Method getConstructor(JvmObject constructorObj) {
        return getMethod(constructorObj, true);
    }

    private static OperandStack convertArgs(JvmObject self, Method method) {
        if (method.getArgSlotCount() == 0) {

        }
        OperandStack ops = new OperandStack(method.getArgSlotCount());
        if (!method.isStatic()) {
            ops.pushRef(self);
        }
        if (method.getArgSlotCount() == 1 && !method.isStatic()) {
            return ops;
        }
        return ops;
    }

    private static Method getMethod(JvmObject methodObj, boolean isConstructor) {
        Object extra = methodObj.getExtra();
        if (extra != null) {
            return (Method) extra;
        }
        if (isConstructor) {
            JvmObject root = methodObj.getRefVar("root", "Ljava/lang/reflect/Constructor;");
            return (Method) root.getExtra();
        } else {
            JvmObject root = methodObj.getRefVar("root", "Ljava/lang/reflect/Method;");
            return (Method) root.getExtra();
        }
    }


}
