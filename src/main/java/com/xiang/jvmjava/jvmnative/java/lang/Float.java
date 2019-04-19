package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.jvmnative.Registry;

import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/12 19:47
 * @comment
 */

public class Float {

    private static final java.lang.String CLASS_STR = "java/lang/Float";

    private static Function<Frame> floatToRawIntBits = frame -> {
        float val = frame.getLocalVars().getFloat(0);
        frame.getOperandStack().pushInt(java.lang.Float.floatToIntBits(val));
        
    };

    private static Function<Frame> intBitsToFloat = frame -> {
        int val = frame.getLocalVars().getInt(0);
        frame.getOperandStack().pushFloat(java.lang.Float.intBitsToFloat(val));
        
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "floatToRawIntBits", "(F)I", floatToRawIntBits);
        Registry.register(CLASS_STR, "intBitsToFloat", "(I)F", intBitsToFloat);
    }

}
