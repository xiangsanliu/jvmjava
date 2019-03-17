package com.xiang.jvmjava.classfile.rtda;

import java.util.EmptyStackException;

/**
 * @author 项三六
 * @time 2019/3/16 20:03
 * @comment
 */

public class JvmStack {

    private int maxSize;

    private int size;

    private Frame top;

    private JvmStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public static JvmStack newStack(int maxSize) {
        return new JvmStack(maxSize);
    }

    public void push(Frame frame) {
        if (this.size >= this.maxSize) {
            throw new StackOverflowError();
        }
        if (this.top != null) {
            frame.setLower(this.top);
        }
        this.top = frame;
        this.size++;
    }

    public Frame pop() {
        if (this.top == null) {
            throw new EmptyStackException();
        }
        Frame frame = this.top;
        this.top = frame.getLower();
        frame.setLower(null);
        this.size--;
        return frame;
    }

    public Frame top() {
        if (this.top == null) {
            throw new EmptyStackException();
        }
        return this.top;
    }

}
