package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;

import java.util.Arrays;

/**
 * @author 项三六
 * @time 2019/3/16 19:35
 * @comment
 */

public class LocalVars {

    private Slot[] vars;

    public LocalVars(Slot[] vars) {
        this.vars = vars;
    }

    public static LocalVars newLocalVars(int maxLocals) {
        if (maxLocals > 0) {
            return new LocalVars(new Slot[maxLocals]);
        }
        return null;
    }

    public void setInt(int index, int val) {
        this.vars[index] = new Slot();
        this.vars[index].num32 = val;
    }

    public void setFloat(int index, float val) {
        this.vars[index] = new Slot();
        this.vars[index].num32 = Float.floatToIntBits(val);
    }

    public void setLong(int index, long val) {
        this.vars[index] = new Slot();
        this.vars[index].num64 = val;
    }

    public void setDouble(int index, double val) {
        setLong(index, Double.doubleToLongBits(val));
    }

    public void setRef(int index, JvmObject ref) {
        this.vars[index] = new Slot();
        this.vars[index].ref = ref;
    }

    public int getInt(int index) {
        return this.vars[index].num32;
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(this.vars[index].num32);
    }

    public long getLong(int index) {
        return this.vars[index].num64;
    }

    public double getDouble(int index) {
        return Double.longBitsToDouble(this.vars[index].num64);
    }

    public JvmObject getRef(int index) {
        return this.vars[index].ref;
    }

    @Override
    public String toString() {
        return Arrays.toString(vars);
    }
}
