package com.xiang.jvmjava.rtda.heap.member;

import com.xiang.jvmjava.classfile.MemberInfo;
import com.xiang.jvmjava.classfile.attribute.ConstantValueAttribute;
import com.xiang.jvmjava.rtda.heap.AccessFlags;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/23 17:18
 * @comment
 */

@Getter
@Setter
public class Field extends ClassMember {

    private int slotId;

    private int constantValueIndex;

    public static Field[] newFields(JvmClass clazz, MemberInfo[] memberInfos) {
        Field[] fields = new Field[memberInfos.length];
        for (int i = 0; i < memberInfos.length; i++) {
            fields[i] = new Field();
            fields[i].clazz = clazz;
            fields[i].copyMemberInfo(memberInfos[i]);
            fields[i].copyAttributes(memberInfos[i]);
        }
        return fields;
    }

    public JvmClass getType() {
        return clazz.getLoader().loadClass(JvmClass.toClassName(descriptor));
    }

    public boolean isLongOrDouble() {
        return "J".equals(this.descriptor) || "D".equals(this.descriptor);
    }

    public boolean isVolatile() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VOLATILE);
    }

    public boolean isTransient() {
        return 0 != (this.accessFlags & AccessFlags.ACC_TRANSIENT);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ENUM);
    }

    @Override
    void copyAttributes(MemberInfo info) {
        ConstantValueAttribute attr = info.getConstantValueAttribute();
        if (attr != null) {
            this.constantValueIndex = (int) Integer.toUnsignedLong(attr.getConstantValueIndex());
        }
    }
}
