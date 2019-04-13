package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;

import java.util.Stack;

/**
 * @author 项三六
 * @time 2019/3/16 18:56
 * @comment
 */
public class OperandStack {

    private int maxStack;

    private Stack<Slot> slots;

    public OperandStack(int maxStack) {
        this.maxStack = maxStack;
        this.slots = new Stack<>();
    }

    public void pushInt(int val) {
        checkStackSize();
        Slot slot = new Slot();
        slot.setNum32(val);
        this.slots.push(slot);
    }

    public int popInt() {
        return this.slots.pop().getNum32();
    }

    public void pushFloat(float val) {
        checkStackSize();
        Slot slot = new Slot();
        slot.setNum32(Float.floatToIntBits(val));
        this.slots.push(slot);
    }

    public float popFloat() {
        return Float.intBitsToFloat(this.slots.pop().getNum32());
    }

    public void pushLong(long val) {
        checkStackSize();
        Slot slot = new Slot();
        slot.setNum64(val);
        this.slots.push(slot);
        this.slots.push(null);
    }

    public long popLong() {
        this.slots.pop();
        return this.slots.pop().getNum64();
    }

    public void pushDouble(double val) {
        checkStackSize();
        this.pushLong(Double.doubleToLongBits(val));
    }

    public double popDouble() {
        return Double.longBitsToDouble(this.popLong());
    }

    public void pushRef(JvmObject ref) {
        checkStackSize();
        Slot slot = new Slot();
        slot.setRef(ref);
        this.slots.push(slot);
    }

    public JvmObject popRef() {
        return this.slots.pop().getRef();
    }

    public void pushBoolean(boolean val) {
        if (val) {
            this.pushInt(1);
        } else {
            this.pushInt(0);
        }
    }

    public boolean popBoolean() {
        return this.popInt() == 1;
    }

    public void pushSlot(Slot slot) {
        checkStackSize();
        this.slots.push(slot);
    }

    public Slot popSlot() {
        return this.slots.pop();
    }

    public JvmObject getRefFromTop(int n) {
        return this.slots.get(this.slots.size() - 1 - n).getRef();
    }

    public void clear() {
        this.slots.clear();
    }

    @Override
    public String toString() {
        return slots.toString();
    }

    private void checkStackSize() {
        if (this.slots.size() > this.maxStack) {
            throw new StackOverflowError();
        }
    }
}
