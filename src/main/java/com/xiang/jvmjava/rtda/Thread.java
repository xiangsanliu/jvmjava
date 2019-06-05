package com.xiang.jvmjava.rtda;

import com.xiang.jvmjava.rtda.heap.member.Method;

import java.util.ArrayList;
import java.util.List;
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

    public Frame newFrame(Method method) {
        return new Frame(this, method);
    }

    public List<Frame> getFrames(int skip) {
        return this.stack.subList(0, this.stack.size() - skip);
    }

    public List<Frame> getAllFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = stack.size() - 1; i >= 0; i--) {
            frames.add(stack.get(i));
        }
        return frames;
    }

    public boolean isStackEmpty() {
        return this.stack.isEmpty();
    }

    public void clearStack() {
        stack.clear();
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public int getPC() {
        return this.pc;
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
