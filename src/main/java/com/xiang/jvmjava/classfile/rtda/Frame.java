package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
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
        this.localVars = new Slots(method.getMaxLocals());
        this.operandStack = new OperandStack(method.getMaxStack());
    }

    public void revertNextPC() {
        this.nextPC = this.thread.getPc();
    }

    public Frame(Slots localVars, OperandStack operandStack, Thread thread) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }


}
