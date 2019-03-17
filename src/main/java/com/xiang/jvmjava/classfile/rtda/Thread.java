package com.xiang.jvmjava.classfile.rtda;

/**
 * @author 项三六
 * @time 2019/3/16 18:56
 * @comment
 */
/*
JVM
  Thread
    pc
    Stack
      Frame
        LocalVars
        OperandStack
*/

public class Thread {

    private int pc;

    private JvmStack stack;

    private Thread(int maxSize) {
        this.stack = JvmStack.newStack(maxSize);
    }

    public Thread newThread() {
        return new Thread(1024);
    }

    public void pushFrame(Frame frame) {
        this.stack.push(frame);
    }

    public Frame popFrame() {
        return this.stack.pop();
    }

    public Frame currentFrame() {
        return this.stack.top();
    }

}
