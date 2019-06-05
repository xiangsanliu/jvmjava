package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.OperandStack;
import com.xiang.jvmjava.rtda.Thread;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.StringPool;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;
import com.xiang.jvmjava.jvmnative.java.lang.Throwable;

/**
 * @author 项三六
 * @time 2019/4/13 10:55
 * @comment
 */

public class AThrow extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {

        JvmObject exception = frame.getOperandStack().popRef();
        if (exception == null) {
            throw new NullPointerException();
        }
        Thread thread = frame.getThread();
        if (!check(thread, exception)) {
            handleUncaughtException(thread, exception);
        }

    }

    // 检查是否可以找到并跳转到异常处理代码
    private boolean check(Thread thread, JvmObject exception) {
        do {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPC() - 1;
            int handlerPC = frame.getMethod().findExceptionHandlerPC(exception.getClazz(), pc);
            if (handlerPC > 0) {
                OperandStack stack = frame.getOperandStack();
                stack.clear();
                stack.pushRef(exception);
                frame.setNextPC(handlerPC);
                return true;
            }
            thread.popFrame();
        } while (!thread.isStackEmpty());
        return false;
    }

    private void handleUncaughtException(Thread thread, JvmObject exception) {
        thread.clearStack();
        JvmObject msg = exception.getRefVar("detailMessage", "Ljava/lang/String;");
        String jvmMsg = StringPool.jvmStrToString(msg);
        System.err.println(exception.getClazz().getClassName() + ": " + jvmMsg);
        for (Throwable.StackTraceElement element : (Throwable.StackTraceElement[]) exception.getExtra()) {
            System.err.println(element.toString());
        }
    }

}
