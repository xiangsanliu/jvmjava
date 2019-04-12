package com.xiang.jvmjava.instruction.reserved;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;
import com.xiang.jvmjava.jvmnative.Registry;

import java.util.function.Function;

/**
 * @author 项三六
 * @time 2019/4/11 19:41
 * @comment
 */

public class InvokeNative extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Method method = frame.getMethod();
        String className = method.getClazz().getName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();
        Function<Frame, Void> nativeMethod = Registry.findNativeMethod(className, methodName, methodDescriptor);
        if (nativeMethod == null) {
            throw new UnsatisfiedLinkError(String.format("%s.%s%s", className, methodName, methodDescriptor));
        }
        nativeMethod.apply(frame);
    }
}
