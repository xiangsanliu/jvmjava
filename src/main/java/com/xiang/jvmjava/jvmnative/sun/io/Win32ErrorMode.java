package com.xiang.jvmjava.jvmnative.sun.io;

import com.xiang.jvmjava.jvmnative.Registry;

/**
 * @author 项三六
 * @time 2019/4/19 10:31
 * @comment
 */

public class Win32ErrorMode {

    public static void registerNatives() {
        Registry.register("sun/io/Win32ErrorMode", "setErrorMode",
                "(J)J", frame -> frame.getOperandStack().pushLong(0L));
    }

}
