
package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.rtda.Slots;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/23 16:50
 * @comment
 */
@NoArgsConstructor
public class JvmObject {

    @Getter
    private JvmClass clazz;

    @Setter
    private Object data;

    public JvmObject(JvmClass clazz) {
        this.clazz = clazz;
        this.data = new Slots(clazz.getInstanceSlotCount());
    }

    public Slots getFields() {
        return (Slots) this.data;
    }

    public boolean isInstantOf(JvmClass clazz) throws IOException {
        return clazz.isAssignableFrom(this.clazz);
    }

    public byte[] getBytes() {
        return (byte[]) this.data;
    }

    public short[] getShorts() {
        return (short[]) this.data;
    }

    public int[] getInts() {
        return (int[]) this.data;
    }

    public long[] getLongs() {
        return (long[]) this.data;
    }

    public char[] getChars() {
        return (char[]) this.data;
    }

    public float[] getFloats() {
        return (float[]) this.data;
    }

    public double[] getDoubles() {
        return (double[]) this.data;
    }

    public JvmObject[] getRefs() {
        return (JvmObject[]) this.data;
    }

    public int getArrayLength() {
        if (this.data instanceof byte[]) {
            return getBytes().length;
        } else if (this.data instanceof short[]) {
            return getShorts().length;
        } else if (this.data instanceof int[]) {
            return getInts().length;
        } else if (this.data instanceof long[]) {
            return getLongs().length;
        } else if (this.data instanceof char[]) {
            return getChars().length;
        } else if (this.data instanceof float[]) {
            return getFloats().length;
        } else if (this.data instanceof double[]) {
            return getDoubles().length;
        } else {
            return getRefs().length;
        }
    }

}
