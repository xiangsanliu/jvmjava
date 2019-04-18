package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 18:59
 * @comment
 */

public class System {

    private static final java.lang.String classStr = "java/lang/System";

    private static Function<Frame, Void> arraycopy = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject src = vars.getRef(0);
        int srcPos = vars.getInt(1);
        JvmObject dest = vars.getRef(2);
        int destPos = vars.getInt(3);
        int length = vars.getInt(4);
        java.lang.System.arraycopy(src.getData(), srcPos, dest.getData(), destPos, length);
        return null;
    };

    private static Function<Frame, Void> registerNatives = frame -> {
        Registry.register(classStr, "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", arraycopy);
        return null;
    };

    public static void registerNatives() {
        Registry.register(classStr, "registerNatives", "()V", registerNatives);
    }


}
