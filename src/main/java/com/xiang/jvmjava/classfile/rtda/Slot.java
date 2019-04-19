package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/16 18:56
 * @comment
 */

@Setter
@Getter
public class Slot {

    private int num32;

    private long num64;

    private JvmObject ref;

    public Slot() {
        this.num32 = 0;
        this.num64 = 0;
        this.ref = null;
    }

    public Slot(Slot slot) {
        if (slot != null) {
            this.num32 = slot.num32;
            this.num64 = slot.num64;
            this.ref = slot.ref;
        }
    }

    @Override
    public String toString() {
        if (ref != null) {
            return "Slot @" + hashCode() + "{" +
                    "num32=" + num32 +
                    ", num64=" + num64 +
                    ", ref=" + ref.getClazz().getName() +
                    '}';
        } else {
            return "Slot @" + hashCode() + "{" +
                    "num32=" + num32 +
                    ", num64=" + num64 +
                    ", ref=" + ref +
                    '}';
        }
    }
}
