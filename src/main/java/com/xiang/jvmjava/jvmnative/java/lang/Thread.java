package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;

import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/13 16:53
 * @comment
 */

public class Thread {

    private static final java.lang.String CLASS_STR = "java/lang/Thread";

    private static Function<Frame> currentThread = frame -> {
        ClassLoader loader = frame.getMethod().getClazz().getLoader();
        JvmClass threadClass = loader.loadClass(CLASS_STR);
        JvmObject threadObject = threadClass.newObject();
        JvmClass threadGroupClass = loader.loadClass("java/lang/ThreadGroup");
        JvmObject threadGroupObject = threadGroupClass.newObject();
        threadObject.setRefVar("group", "Ljava/lang/ThreadGroup;", threadGroupObject);
        threadObject.setIntVar("priority", "I", 1);
        frame.getOperandStack().pushRef(threadObject);
        
    };

    private static Function<Frame> setPriority0 = frame -> {};

    private static Function<Frame> isAlive = frame -> {
        frame.getOperandStack().pushBoolean(false);
        
    };

    private static Function<Frame> start0 = frame -> {};


    private static Function<Frame> registerNatives = frame -> {
        Registry.register("java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", currentThread);
        Registry.register("java/lang/Thread", "setPriority0", "(I)V", setPriority0);
        Registry.register("java/lang/Thread", "isAlive", "()Z", isAlive);
        Registry.register("java/lang/Thread", "start0", "()V", start0);
        
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", registerNatives);
    }


}
