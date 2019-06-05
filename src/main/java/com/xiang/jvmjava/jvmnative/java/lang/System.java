package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.Slots;
import com.xiang.jvmjava.rtda.Thread;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.StringPool;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.jvmnative.Registry;
import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/12 18:59
 * @comment
 */

public class System {

    private static final java.lang.String CLASS_STR = "java/lang/System";

    private static Function<Frame> arraycopy = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject src = vars.getRef(0);
        int srcPos = vars.getInt(1);
        JvmObject dest = vars.getRef(2);
        int destPos = vars.getInt(3);
        int length = vars.getInt(4);
        java.lang.System.arraycopy(src.getData(), srcPos, dest.getData(), destPos, length);

    };

    private static Function<Frame> initProperties = frame -> {
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

    };
    private static Function<Frame> setOut0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject out = vars.getRef(0);
        JvmClass systemClass = frame.getMethod().getClazz();
        systemClass.setRefVar("out", "Ljava/io/PrintStream;", out);

    };

    private static Function<Frame> setIn0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject in = vars.getRef(0);
        JvmClass systemClass = frame.getMethod().getClazz();
        systemClass.setRefVar("in", "Ljava/io/InputStream;", in);

    };

    private static Function<Frame> setErr0 = frame -> {
        Slots vars = frame.getLocalVars();
        JvmObject err = vars.getRef(0);
        JvmClass systemClass = frame.getMethod().getClazz();
        systemClass.setRefVar("err", "Ljava/io/PrintStream;", err);
    };


    public static void registerNatives() {
        Registry.register(CLASS_STR, "registerNatives", "()V", frame -> {
        });

        Registry.register(CLASS_STR, "arraycopy",
                "(Ljava/lang/Object;ILjava/lang/Object;II)V", arraycopy);

        Registry.register(CLASS_STR, "initProperties",
                "(Ljava/util/Properties;)Ljava/util/Properties;", initProperties);

        Registry.register(CLASS_STR, "setIn0", "(Ljava/io/InputStream;)V", setIn0);

        Registry.register(CLASS_STR, "setOut0", "(Ljava/io/PrintStream;)V", setOut0);

        Registry.register(CLASS_STR, "setErr0", "(Ljava/io/PrintStream;)V", setErr0);

        Registry.register(CLASS_STR, "currentTimeMillis",
                "()J", frame -> frame.getOperandStack().pushLong(java.lang.System.currentTimeMillis()));

        Registry.register(CLASS_STR, "mapLibraryName",
                "(Ljava/lang/String;)Ljava/lang/String;", frame -> {
                    JvmObject jvmStr = frame.getLocalVars().getRef(0);
                    java.lang.String libName = StringPool.jvmStrToString(jvmStr);
                    frame.getOperandStack().pushRef(
                            StringPool.getJvmString(
                                    frame.getMethod().getClazz().getLoader(), java.lang.System.mapLibraryName(libName)
                            )
                    );

                });
    }


}
