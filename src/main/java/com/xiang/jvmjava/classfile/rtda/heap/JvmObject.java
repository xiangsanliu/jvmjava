
package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.rtda.Slots;

/**
 * @author 项三六
 * @time 2019/3/23 16:50
 * @comment
 */
public class JvmObject {

    private JvmClass clazz;

    private Object data;

    public JvmObject(JvmClass clazz) {
        this.clazz = clazz;
        this.data = new Slots(clazz.getInstanceSlotCount());
    }

    public Slots getFields() {
        return (Slots) this.data;
    }

    public boolean isInstantOf(JvmClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

    public Byte[] getBytes() {
        return (Byte[]) this.data;
    }

    public Short[] getShorts() {
        return (Short[]) this.data;
    }

    public Integer[] getIntegers() {
        return (Integer[]) this.data;
    }



}
