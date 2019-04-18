package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.jvmnative.Registry;

import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/12 19:40
 * @comment
 */

public class Double {

    private static final java.lang.String CLASS_STR = "java/lang/Double";

    private static Function<Frame> doubleToRawLongBits = frame -> {
        double val = frame.getLocalVars().getDouble(0);
        frame.getOperandStack().pushLong(java.lang.Double.doubleToLongBits(val));
        
    };

    private static Function<Frame> longBitsToDouble = frame -> {
        long val = frame.getLocalVars().getLong(0);
        frame.getOperandStack().pushDouble(java.lang.Double.longBitsToDouble(val));
        
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "doubleToRawLongBits", "(D)J", doubleToRawLongBits);
        Registry.register(CLASS_STR, "longBitsToDouble", "(J)D", longBitsToDouble);
    }

}
