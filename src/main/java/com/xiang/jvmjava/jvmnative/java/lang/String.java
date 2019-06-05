package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.StringPool;
import com.xiang.jvmjava.jvmnative.Registry;

import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/12 19:25
 * @comment
 */

public class String {

    private static final java.lang.String CLASS_STR = "java/lang/String";

    private static Function<Frame> intern = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        frame.getOperandStack().pushRef(StringPool.internString(self));
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "intern", "()Ljava/lang/String;", intern);
    }

}
