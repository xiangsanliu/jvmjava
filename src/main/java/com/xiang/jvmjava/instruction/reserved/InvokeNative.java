package com.xiang.jvmjava.instruction.reserved;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.NoOperandsInstruction;
import com.xiang.jvmjava.jvmnative.Registry;

import com.xiang.jvmjava.util.Function;

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
        Function<Frame> nativeMethod = Registry.findNativeMethod(className, methodName, methodDescriptor);
        if (nativeMethod == null) {
            throw new UnsatisfiedLinkError(String.format("%s.%s%s", method.getClazz().getClassName(), methodName, methodDescriptor));
        }
        nativeMethod.execute(frame);
    }
}
