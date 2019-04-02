
package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.rtda.Slots;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/23 16:50
 * @comment
 */
@Getter
public class JvmObject {

    private JvmClass clazz;

    private Slots fields;

    public JvmObject(JvmClass clazz) {
        this.clazz = clazz;
        this.fields = new Slots(clazz.getInstanceSlotCount());
    }

    public boolean isInstantOf(JvmClass clazz) {
        return clazz.isAssignableFrom(this.clazz);
    }

}
