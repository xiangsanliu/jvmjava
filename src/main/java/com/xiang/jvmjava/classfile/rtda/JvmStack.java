package com.xiang.jvmjava.classfile.rtda;

import java.util.Stack;

/**
 * @author 项三六
 * @time 2019/3/16 20:03
 * @comment
 */

public class JvmStack {

    private int maxSize;

    private Stack<Frame> stack;

    JvmStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Stack<>();
    }


    void push(Frame frame) {
        if (this.stack.size() >= this.maxSize) {
            throw new StackOverflowError();
        }
        stack.push(frame);
    }

    Frame pop() {
        return this.stack.pop();
    }

    Frame top() {
        return this.stack.peek();
    }

    boolean isEmpty() {
        return this.stack.isEmpty();
    }

}
