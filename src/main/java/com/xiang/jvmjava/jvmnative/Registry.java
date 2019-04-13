package com.xiang.jvmjava.jvmnative;

import com.xiang.jvmjava.classfile.rtda.Frame;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/11 16:53
 * @comment
 */

public class Registry {

    private static Map<String, Function<Frame, Void>> registry = new HashMap<>();

    public static void register(String className, String methodName, String methodDescriptor, Function<Frame, Void> method) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        registry.put(key, method);
    }

    public static Function<Frame, Void> findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        Function<Frame, Void> method = registry.get(key);
        if (method != null) {
            return method;
        }
        if (methodDescriptor.equals("()V") && methodName.equals("registerNatives")) {
            return frame -> null;
        }
        return null;
    }

    static {
        com.xiang.jvmjava.jvmnative.java.lang.Class.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Object.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.System.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.String.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Double.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Float.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Throwable.registerNatives();
    }

}
