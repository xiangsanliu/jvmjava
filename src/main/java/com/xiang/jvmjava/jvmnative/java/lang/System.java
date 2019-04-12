package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
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
        if (src == null || dest == null) {
            throw new NullPointerException();
        }
        if (!checkArrayCopy(src, dest)) {
            throw new ArrayStoreException();
        }
        if (srcPos < 0 || destPos < 0 || length < 0 ||
                srcPos + length > src.getArrayLength() ||
                destPos + length > dest.getArrayLength()) {
            throw new IndexOutOfBoundsException();
        }
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

    private static boolean checkArrayCopy(JvmObject src, JvmObject dest) {
        JvmClass srcClass = src.getClazz();
        JvmClass destClass = dest.getClazz();
        if (!srcClass.isArray() || !destClass.isArray()) {
            return false;
        }
        if ((srcClass.getElementClass().isPrimitive()) || destClass.getElementClass().isPrimitive()) {
            return srcClass.equals(destClass);
        }
        return true;
    }


}
