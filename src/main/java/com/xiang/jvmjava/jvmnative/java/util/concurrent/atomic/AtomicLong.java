package com.xiang.jvmjava.jvmnative.java.util.concurrent.atomic;

import com.xiang.jvmjava.jvmnative.Registry;

/**
 * @author 项三六
 * @time 2019/4/18 23:04
 * @comment
 */

public class AtomicLong {

    public static void registerNatives() {

        Registry.register("java/util/concurrent/atomic/AtomicLong", "VMSupportsCS8", "()Z", frame -> {
            frame.getOperandStack().pushInt(0);
        });

    }
}
