package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;

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

    public Slots(Slot[] vars) {
        this.vars = vars;
    }

    public Slots(int size) {
        this.vars = new Slot[size];
        for (int i = 0; i < size; i++) {
            this.vars[i] = new Slot();
        }
    }

    public static Slots newSlots(int count) {
        if (count > 0) {
            return new Slots(new Slot[count]);
        }
        return null;
    }

    public static Slots newLocalVars(int maxLocals) {
        if (maxLocals > 0) {
            return new Slots(new Slot[maxLocals]);
        }
        return null;
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


    @Override
    public String toString() {
        return Arrays.toString(vars);
    }
}
