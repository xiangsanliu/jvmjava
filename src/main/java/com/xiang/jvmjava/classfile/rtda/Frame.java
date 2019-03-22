package com.xiang.jvmjava.classfile.rtda;

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
    private LocalVars localVars;

    @Getter
    private OperandStack operandStack;

    @Getter
    private Thread thread;

    @Setter
    @Getter
    private int nextPC;

    private Frame(LocalVars localVars, OperandStack operandStack, Thread thread) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }

    public static Frame newFrame(Thread thread, int maxLocals, int maxStack) {
        return new Frame(LocalVars.newLocalVars(maxLocals), OperandStack.newOperandStack(maxStack), thread);
    }


}
