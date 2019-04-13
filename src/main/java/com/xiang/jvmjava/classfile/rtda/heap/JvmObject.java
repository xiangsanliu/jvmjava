
package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.member.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author 项三六
 * @time 2019/3/23 16:50
 * @comment
 */
@Getter
@Setter
@NoArgsConstructor
public class JvmObject {

    private JvmClass clazz;

    private Object data;

    private Object extra;

    public JvmObject(JvmClass clazz) {
        this.clazz = clazz;
        this.data = new Slots(clazz.getInstanceSlotCount());
    }

    public JvmObject(JvmClass clazz, Object data) {
        this.clazz = clazz;
        this.data = data;
    }

    public Slots getFields() {
        return (Slots) this.data;
    }

    public boolean isInstantOf(JvmClass clazz) {
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

    void setRefVar(String name, String descriptor, JvmObject ref) {
        Field field = this.clazz.getField(name, descriptor, false);
        Slots slots = (Slots) this.data;
        slots.setRef(field.getSlotId(), ref);
    }

    public JvmObject getRefVar(String name, String descriptor) {
        Field field = this.clazz.getField(name, descriptor, false);
        Slots slots = (Slots) this.data;
        return slots.getRef(field.getSlotId());
    }

    public JvmObject jvmClone() {
        JvmObject clone = new JvmObject();
        clone.setClazz(this.clazz);
        if (this.data instanceof byte[]) {
            clone.setData(ArrayUtils.clone(getBytes()));
        } else if (this.data instanceof short[]) {
            clone.setData(ArrayUtils.clone(getShorts()));
        } else if (this.data instanceof int[]) {
            clone.setData(ArrayUtils.clone(getInts()));
        } else if (this.data instanceof long[]) {
            clone.setData(ArrayUtils.clone(getLongs()));
        } else if (this.data instanceof char[]) {
            clone.setData(ArrayUtils.clone(getShorts()));
        } else if (this.data instanceof float[]) {
            clone.setData(ArrayUtils.clone(getFloats()));
        } else if (this.data instanceof double[]) {
            clone.setData(ArrayUtils.clone(getDoubles()));
        } else if (this.data instanceof JvmObject[]) {
            clone.setData(ArrayUtils.clone(getRefs()));
        } else {
            Slots slots = (Slots) this.data;
            clone.setData(slots.jvmClone());
        }
        return clone;
    }

    @Override
    public String toString() {
        return this.getClazz().getName();
    }
}
