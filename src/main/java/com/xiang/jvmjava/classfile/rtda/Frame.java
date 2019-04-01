package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.Method;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/16 18:56
 * @comment
 */

public class Frame {

    @Getter
    @Setter
    private Frame lower;

    @Getter
    private Slots localVars;

    @Getter
    private OperandStack operandStack;

    @Getter
    private Thread thread;

    @Getter
    private Method method;

    @Setter
    @Getter
    private int nextPC;

    public Frame(Thread thread, Method method) {
        this.thread = thread;
        this.method = method;
        this.localVars = Slots.newLocalVars(method.getMaxLocals());
        this.operandStack = OperandStack.newOperandStack(method.getMaxStack());
    }

    public void revertNextPC() {
        this.nextPC = this.thread.getPc();
    }


    static Frame newFrame(Thread thread, int maxLocals, int maxStack) {
        return new Frame(Slots.newLocalVars(maxLocals), OperandStack.newOperandStack(maxStack), thread);
    }

    private Frame(Slots localVars, OperandStack operandStack, Thread thread) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }


}
