package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.Method;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

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

    @Getter
    @Setter
    private int pc;

    private Stack<Frame> stack;

    private int maxSize;

    public Thread() {
        this(1024);
    }

    private Thread(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Stack<>();
    }

    public void pushFrame(Frame frame) {
        if (this.stack.size() > this.maxSize) {
            throw new StackOverflowError();
        }
        this.stack.push(frame);
    }

    public Frame popFrame() {
        return this.stack.pop();
    }

    public Frame topFrame() {
        return this.stack.peek();
    }

    public Frame currentFrame() {
        return this.stack.peek();
    }

    public Frame newFrame(int maxLocals, int maxStack) {
        return Frame.newFrame(this, maxLocals, maxStack);
    }

    public Frame newFrame(Method method) {
        return new Frame(this, method);
    }

    public boolean isStackEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
