package com.xiang.jvmjava.jvmnative.java.io;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/13 16:39
 * @comment
 */

public class FileOutputStream {

    private static Function<Frame, Void> writeBytes = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject b = vars.getRef(1);
        int off = vars.getInt(2);
        int len = vars.getInt(3);
        byte[] bytes = b.getBytes();
        System.out.write(bytes, off, off + len);
        return null;
    };

    public static void registerNatives() {
        Registry.register("java/io/FileOutputStream", "writeBytes", "([BIIZ)V", writeBytes);
    }

}