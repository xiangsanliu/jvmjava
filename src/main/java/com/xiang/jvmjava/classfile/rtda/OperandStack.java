package com.xiang.jvmjava.classfile.rtda;

/**
 * @author 项三六
 * @time 2019/3/16 18:56
 * @comment
 */

public class OperandStack {

    private int size;

    private Slot[] slots;

    public OperandStack(Slot[] slots) {
        this.slots = slots;
        this.size = 0;
    }

    public static OperandStack newOperandStack(int maxStack) {
        if (maxStack > 0) {
            return new OperandStack(new Slot[maxStack]);
        }
        return null;
    }

    public void pushInt(int val) {
        this.slots[this.size] = new Slot();
        this.slots[this.size++].num32 = val;
    }

    public int popInt() {
        return this.slots[--this.size].num32;
    }

    public void pushFloat(float val) {
        this.slots[this.size] = new Slot();
        this.slots[this.size++].num32 = Float.floatToIntBits(val);
    }

    public float popFloat() {
        return Float.intBitsToFloat(this.slots[--this.size].num32);
    }

    public void pushLong(long val) {
        this.slots[this.size] = new Slot();
        this.slots[this.size++].num64 = val;
    }

    public long popLong() {
        return this.slots[--this.size].num64;
    }

    public void pushDouble(double val) {
        this.pushLong(Double.doubleToLongBits(val));
    }

    public double popDouble() {
        return Double.longBitsToDouble(this.popLong());
    }

    public void pushRef(Object ref) {
        this.slots[this.size] = new Slot();
        this.slots[this.size++].ref = ref;
    }

    public Object popRef() {
        this.size--;
        Object ref = this.slots[this.size].ref;
        this.slots[this.size].ref = null;
        return ref;
    }

}
