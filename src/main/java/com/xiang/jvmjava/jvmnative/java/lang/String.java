package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 19:25
 * @comment
 */

public class String {

    private static final java.lang.String classStr = "java/lang/String";

    private static Function<Frame, Void> intern = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        frame.getOperandStack().pushRef(StringPool.internString(self));
        return null;
    };

    public static void registerNatives() {
        Registry.register(classStr, "intern", "()Ljava/lang/String;", intern);
    }

}
