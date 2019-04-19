package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;

/**
 * @author 项三六
 * @time 2019/4/19 10:22
 * @comment
 */

public class ClassLoader$NativeLibrary {

    public static void registerNatives() {
        Registry.register("java/lang/ClassLoader$NativeLibrary", "load", "(Ljava/lang/String;Z)V", frame -> {
            JvmObject libObject = frame.getLocalVars().getRef(0);
            libObject.setIntVar("loaded", "Z", 1);
        });
    }

}
