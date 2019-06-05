package com.xiang.jvmjava.instruction.base;

import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.Slot;
import com.xiang.jvmjava.rtda.Thread;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.BytecodeReader;

/**
 * @author 项三六
 * @time 2019/3/17 17:44
 * @comment
 */

public abstract class Instruction {

    public abstract void fetchOperands(BytecodeReader reader);

    public abstract void execute(Frame frame);

    public static void branch(Frame frame, int offset) {
        int pc = frame.getThread().getPC();
        frame.setNextPC(pc + offset);
    }

    // 新建栈帧，把参数放入栈帧
    public static void invokeMethod(Frame invokerFrame, Method method) {
        Thread thread = invokerFrame.getThread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);
        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
    }

    public static void initClass(Thread thread, JvmClass clazz) {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, JvmClass clazz) {
        Method clinit = clazz.getClinitMethod();
        if (clinit != null && clinit.getClazz() == clazz) {
            // exec <clinit>
            Frame newFrame = thread.newFrame(clinit);
            thread.pushFrame(newFrame);
        }
    }

    private static void initSuperClass(Thread thread, JvmClass clazz) {
        if (!clazz.isInterface()) {
            JvmClass superClazz = clazz.getSuperClass();
            if (superClazz != null && !superClazz.isInitStarted()) {
                initClass(thread, superClazz);
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
