package com.xiang.jvmjava.jvmnative.java.security;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/13 16:45
 * @comment
 */

public class AccessController {

    private static final String CLASS_STR = "java/security/AccessController";

    private static Function<Frame, Void> doPrivileged = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject action = vars.getRef(0);
        OperandStack stack = frame.getOperandStack();
        stack.pushRef(action);
        Method method = action.getClazz().getInstanceMethod("run", "()Ljava/lang/Object;");
        Instruction.invokeMethod(frame, method);
        return null;
    };

    private static Function<Frame, Void> getStackAccessControlContext = frame -> {
        frame.getOperandStack().pushRef(null);
        return null;
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "doPrivileged", "(Ljava/security/PrivilegedAction;)Ljava/lang/Object;", doPrivileged);
        Registry.register(CLASS_STR, "doPrivileged", "(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;", doPrivileged);
        Registry.register(CLASS_STR, "getStackAccessControlContext", "()Ljava/security/AccessControlContext;", getStackAccessControlContext);
    }

}
