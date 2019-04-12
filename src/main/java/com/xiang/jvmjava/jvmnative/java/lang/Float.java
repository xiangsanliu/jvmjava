package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 19:47
 * @comment
 */

public class Float {

    private static final java.lang.String classStr = "java/lang/Float";

    private static Function<Frame, Void> floatToRawIntBits = frame -> {
        float val = frame.getLocalVars().getFloat(0);
        frame.getOperandStack().pushInt(java.lang.Float.floatToIntBits(val));
        return null;
    };

    private static Function<Frame, Void> intBitsToFloat = frame -> {
        int val = frame.getLocalVars().getInt(0);
        frame.getOperandStack().pushFloat(java.lang.Float.intBitsToFloat(val));
        return null;
    };

    public static void registerNatives() {
        Registry.register(classStr, "floatToRawIntBits", "(F)I", floatToRawIntBits);
        Registry.register(classStr, "intBitsToFloat", "(I)F", intBitsToFloat);
    }

}
