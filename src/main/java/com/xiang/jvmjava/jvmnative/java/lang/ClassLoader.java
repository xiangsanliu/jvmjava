package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.jvmnative.Registry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 项三六
 * @time 2019/4/19 9:42
 * @comment
 */

public class ClassLoader {

    public static void registerNatives() {

        Registry.register("java/lang/ClassLoader", "registerNatives", "()V", frame -> {
        });

        Registry.register("java/lang/ClassLoader", "findBuiltinLib", "(Ljava/lang/String;)Ljava/lang/String;", frame -> {
            JvmObject jvmStr = frame.getLocalVars().getRef(0);
            java.lang.String name = StringPool.jvmStrToString(jvmStr);
            try {
                Method findBuiltinLib = java.lang.ClassLoader.class.getDeclaredMethod("findBuiltinLib", java.lang.String.class);
                findBuiltinLib.setAccessible(true);
                java.lang.String build = (java.lang.String) findBuiltinLib.invoke(null, name);
                frame.getOperandStack().pushRef(
                        StringPool.getJvmString(frame.getMethod().getClazz().getLoader(), build)
                );
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });


    }

}
