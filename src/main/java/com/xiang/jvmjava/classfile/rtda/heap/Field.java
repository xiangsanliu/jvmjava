package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.MemberInfo;
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

}
