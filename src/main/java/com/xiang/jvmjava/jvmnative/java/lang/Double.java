package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 19:40
 * @comment
 */

public class Double {

    private static final java.lang.String classStr = "java/lang/Double";

    private static Function<Frame, Void> doubleToRawLongBits = frame -> {
        double val = frame.getLocalVars().getDouble(0);
        frame.getOperandStack().pushLong(java.lang.Double.doubleToLongBits(val));
        return null;
    };

    private static Function<Frame, Void> longBitsToDouble = frame -> {
        long val = frame.getLocalVars().getLong(0);
        frame.getOperandStack().pushDouble(java.lang.Double.longBitsToDouble(val));
        return null;
    };

    public static void registerNatives() {
        Registry.register(classStr, "doubleToRawLongBits", "(D)J", doubleToRawLongBits);
        Registry.register(classStr, "longBitsToDouble", "(J)D", longBitsToDouble);
    }

}
