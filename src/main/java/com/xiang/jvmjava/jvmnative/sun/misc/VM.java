package com.xiang.jvmjava.jvmnative.sun.misc;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/13 14:46
 * @comment
 */

public class VM {

    private static Function<Frame, Void> initialize = frame -> {
        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        JvmClass systemClass = loader.loadClass("java/lang/System");
        Method method = systemClass.getStaticMethod("initializeSystemClass", "()V");
        Instruction.invokeMethod(frame, method);
        return null;
    };

    public static void registerNatives() {
        Registry.register("sun/misc/VM", "initialize", "()V", initialize);
    }

}