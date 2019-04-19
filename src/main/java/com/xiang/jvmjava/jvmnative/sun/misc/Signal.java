package com.xiang.jvmjava.jvmnative.sun.misc;

import com.xiang.jvmjava.jvmnative.Registry;

/**
 * @author 项三六
 * @time 2019/4/19 10:27
 * @comment
 */

public class Signal {

    public static void registerNatives() {
        Registry.register("sun/misc/Signal", "findSignal",
                "(Ljava/lang/String;)I", frame -> frame.getOperandStack().pushInt(0));

        Registry.register("sun/misc/Signal", "handle0",
                "(IJ)J", frame -> frame.getOperandStack().pushLong(0L));
    }

}
