package com.xiang.jvmjava.rtda;

import com.xiang.jvmjava.rtda.heap.JvmObject;

import java.util.Arrays;

/**
 * l
 *
 * @author 项三六
 * @time 2019/3/16 19:35
 * @comment
 */

public class Slots {

    private Slot[] vars;

    public Slots(int size) {
        this.vars = new Slot[size];
        for (int i = 0; i < size; i++) {
            this.vars[i] = new Slot();
        }
    }

    public void setInt(int index, int val) {
        this.vars[index] = new Slot();
        this.vars[index].setNum32(val);
    }

    public void setFloat(int index, float val) {
        this.vars[index] = new Slot();
        this.vars[index].setNum32(Float.floatToIntBits(val));
    }

    public void setLong(int index, long val) {
        this.vars[index] = new Slot();
        this.vars[index].setNum64(val);
    }

    public void setDouble(int index, double val) {
        setLong(index, Double.doubleToLongBits(val));
    }

    public void setRef(int index, JvmObject ref) {
        this.vars[index] = new Slot();
        this.vars[index].setRef(ref);
    }

    public void setSlot(int index, Slot slot) {
        this.vars[index] = slot;
    }


    public int getInt(int index) {
        return this.vars[index].getNum32();
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(this.vars[index].getNum32());
    }

    public long getLong(int index) {
        return this.vars[index].getNum64();
    }

    public double getDouble(int index) {
        return Double.longBitsToDouble(this.vars[index].getNum64());
    }

    public JvmObject getRef(int index) {
        return this.vars[index].getRef();
    }

    public boolean getBoolean(int index) {
        return this.getInt(index) == 1;
    }

    public JvmObject getThis() {
        return this.getRef(0);
    }

    @Override
    public String toString() {
        return Arrays.toString(vars);
    }

    public Slots jvmClone() {
        Slots clone = new Slots(this.vars.length);
        System.arraycopy(this.vars, 0, clone.vars, 0, this.vars.length);
        return clone;
    }
}
