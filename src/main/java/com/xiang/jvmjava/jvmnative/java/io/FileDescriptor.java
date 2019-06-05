package com.xiang.jvmjava.jvmnative.java.io;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.jvmnative.Registry;

import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/13 16:37
 * @comment
 */

public class FileDescriptor {

    private static Function<Frame> set = frame -> {
        frame.getOperandStack().pushLong(0);
    };

    public static void registerNatives() {
        Registry.register("java/io/FileDescriptor", "set", "(I)J", set);
    }

}
