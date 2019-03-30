package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;

import java.util.Arrays;

/**
 * @author 项三六
 * @time 2019/3/16 18:56
 * @comment
 */
public class OperandStack {

    private int size;

    private Slot[] slots;

    private OperandStack(Slot[] slots) {
        this.slots = slots;
        this.size = 0;
    }

    static OperandStack newOperandStack(int maxStack) {
        if (maxStack > 0) {
            return new OperandStack(new Slot[maxStack]);
        }
        return null;
    }

    public void pushInt(int val) {
        this.slots[this.size] = new Slot();
        this.slots[this.size++].setNum32(val);
    }

    public int popInt() {
        return this.slots[--this.size].getNum32();
    }

    public void pushFloat(float val) {
        System.out.println("size: " + this.size);
        this.slots[this.size] = new Slot();
        this.slots[this.size++].setNum32(Float.floatToIntBits(val));
    }

    public float popFloat() {
        System.out.println("size: " + this.size);
        return Float.intBitsToFloat(this.slots[--this.size].getNum32());
    }

    public void pushLong(long val) {
        this.slots[this.size] = new Slot();
        this.slots[this.size++].setNum64(val);
    }

    public long popLong() {
        return this.slots[--this.size].getNum64();
    }

    public void pushDouble(double val) {
        this.pushLong(Double.doubleToLongBits(val));
    }

    public double popDouble() {
        return Double.longBitsToDouble(this.popLong());
    }

    public void pushRef(JvmObject ref) {
        this.slots[this.size] = new Slot();
        this.slots[this.size++].setRef(ref);
    }

    public JvmObject popRef() {
        this.size--;
        JvmObject ref = this.slots[this.size].getRef();
        this.slots[this.size].setRef(null);
        return ref;
    }

    public void pushSlot(Slot slot) {
        this.slots[this.size++] = slot;
    }

    public Slot popSlot() {
        return this.slots[--this.size];
    }

    @Override
    public String toString() {
        return Arrays.toString(slots);
    }
}
