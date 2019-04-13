package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.Thread;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/12 18:59
 * @comment
 */

public class System {

    private static final java.lang.String CLASS_STR = "java/lang/System";

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

    private static Function<Frame, Void> initProperties = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject props = vars.getRef(0);
        OperandStack stack = frame.getOperandStack();
        stack.pushRef(props);
        Method setPropMethod = props.getClazz().getInstanceMethod("setProperty",
                "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
        Thread thread = frame.getThread();
        java.lang.System.getProperties().forEach((key, val) -> {
            JvmObject jvmKey = StringPool.getJvmString(frame.getMethod().getClazz().getLoader(), (java.lang.String) key);
            JvmObject jvmVal = StringPool.getJvmString(frame.getMethod().getClazz().getLoader(), (java.lang.String) val);
            OperandStack ops = new OperandStack(3);
            ops.pushRef(props);
            ops.pushRef(jvmKey);
            ops.pushRef(jvmVal);
            Frame shimFrame = new Frame(thread, ops);
            thread.pushFrame(shimFrame);
            Instruction.invokeMethod(shimFrame, setPropMethod);
        });
        return null;
    };
    private static Function<Frame, Void> setOut0 = frame -> {
        java.lang.System.out.println("setOut0");
        Slots vars = frame.getLocalVars();
        JvmObject out = vars.getRef(0);
        JvmClass systemClass = frame.getMethod().getClazz();
        systemClass.setRefVar("out", "Ljava/io/PrintStream;", out);
        return null;
    };



    private static Function<Frame, Void> registerNatives = frame -> {
        Registry.register(CLASS_STR, "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", arraycopy);
        Registry.register(CLASS_STR, "initProperties", "(Ljava/util/Properties;)Ljava/util/Properties;", initProperties);
//        Registry.register(CLASS_STR, "setIn0", "(Ljava/io/InputStream;)V", setIn0);
        Registry.register(CLASS_STR, "setOut0", "(Ljava/io/PrintStream;)V", setOut0);
//        Registry.register(CLASS_STR, "setErr0", "(Ljava/io/PrintStream;)V", setErr0);
//        Registry.register(CLASS_STR, "currentTimeMillis", "()J", currentTimeMillis);
        return null;
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", registerNatives);
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
