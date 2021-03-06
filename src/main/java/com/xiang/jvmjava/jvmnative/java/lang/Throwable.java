package com.xiang.jvmjava.jvmnative.java.lang;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.Thread;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.jvmnative.Registry;
import lombok.Getter;

import java.util.List;
import com.xiang.jvmjava.util.Function;

/**
 * @author 项三六
 * @time 2019/4/13 9:28
 * @comment
 */

public class Throwable {

    private static final java.lang.String CLASS_STR = "java/lang/Throwable";

    private static Function<Frame> fillInStackTrace = frame -> {
        JvmObject self = frame.getLocalVars().getThis();
        frame.getOperandStack().pushRef(self);
        self.setExtra(createStackTraceElements(self, frame.getThread()));
        
    };

    public static void registerNatives() {
        Registry.register(CLASS_STR, "fillInStackTrace", "(I)Ljava/lang/Throwable;", fillInStackTrace);
    }

    private static StackTraceElement[] createStackTraceElements(JvmObject obj, Thread thread) {
        int skip = distanceToObject(obj.getClazz()) + 2;
        List<Frame> frames = thread.getFrames(skip);
        StackTraceElement[] elements = new StackTraceElement[frames.size()];
        int i = frames.size() - 1;
        for (Frame frame : frames) {
            elements[i] = new StackTraceElement(frame);
            i--;
        }
        return elements;
    }

    private static int distanceToObject(JvmClass clazz) {
        int distance = 0;
        for (JvmClass c = clazz.getSuperClass(); c != null; c = c.getSuperClass()) {
            distance++;
        }
        return distance;
    }

    @Getter
    public static class StackTraceElement {

        private java.lang.String fileName;

        private java.lang.String className;

        private java.lang.String methodName;

        private int lineNumber;

        private StackTraceElement(Frame frame) {
            Method method = frame.getMethod();
            JvmClass clazz = method.getClazz();
            this.fileName = clazz.getSourceFile();
            this.className = clazz.getClassName();
            this.methodName = method.getName();
            this.lineNumber = method.getLineNumber(frame.getNextPC() - 1);
        }

        @Override
        public java.lang.String toString() {
            return "\tat " + className + "." + methodName + "(" + fileName + ":" + lineNumber + ")";
        }
    }

}
