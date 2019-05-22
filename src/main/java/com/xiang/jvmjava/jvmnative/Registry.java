package com.xiang.jvmjava.jvmnative;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.jvmnative.sun.misc.VM;
import com.xiang.jvmjava.util.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 项三六
 * @time 2019/4/11 16:53
 * @comment
 */

public class Registry {

    private static Map<String, Function<Frame>> registry = new HashMap<>();

    static {
        VM.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Class.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Object.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.System.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.String.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Double.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Float.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Throwable.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.Thread.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.ClassLoader.registerNatives();
        com.xiang.jvmjava.jvmnative.java.lang.ClassLoader$NativeLibrary.registerNatives();
        com.xiang.jvmjava.jvmnative.java.io.FileDescriptor.registerNatives();
        com.xiang.jvmjava.jvmnative.java.io.FileOutputStream.registerNatives();
        com.xiang.jvmjava.jvmnative.java.io.WinNTFileSystem.registerNatives();
        com.xiang.jvmjava.jvmnative.java.io.UnixFileSystem.registerNatives();
        com.xiang.jvmjava.jvmnative.java.security.AccessController.registerNatives();
        com.xiang.jvmjava.jvmnative.java.util.concurrent.atomic.AtomicLong.registerNatives();
        com.xiang.jvmjava.jvmnative.sun.misc.Unsafe.registerNatives();
        com.xiang.jvmjava.jvmnative.sun.misc.Signal.registerNatives();
        com.xiang.jvmjava.jvmnative.sun.io.Win32ErrorMode.registerNatives();
        com.xiang.jvmjava.jvmnative.sun.reflect.Reflection.registerNatives();
        com.xiang.jvmjava.jvmnative.sun.reflect.NativeConstructorAccessorImpl.registerNatives();
    }

    // 注册
    public static void register(String className, String methodName,
                                String methodDescriptor, Function<Frame> method) {
        String key = className + "." + methodName + "." + methodDescriptor;
        registry.put(key, method);
    }

    // 查找
    public static Function<Frame> findNativeMethod(
            String className, String methodName, String methodDescriptor) {
        String key = className + "." + methodName + "." + methodDescriptor;
        return registry.get(key);
    }

}
