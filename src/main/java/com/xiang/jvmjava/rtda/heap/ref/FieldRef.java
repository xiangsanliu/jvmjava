package com.xiang.jvmjava.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantMemberRefInfo;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.rtda.heap.member.Field;

/**
 * @author 项三六
 * @time 2019/3/24 20:05
 * @comment
 */

public class FieldRef extends MemberRef {

    private Field field;

    public FieldRef(JvmConstantPool constantPool, ConstantMemberRefInfo info) {
        super(constantPool, info);
    }

    public Field resolvedField() {
        if (this.field == null) {
            resolveFieldRef();
        }
        return this.field;
    }

    // 解析符号引用为直接引用
    private void resolveFieldRef() {
        JvmClass d = this.getConstantPool().getClazz();
        JvmClass c = this.resolvedClass();
        this.field = lookupField(c, this.getName(), this.getDescriptor());
        if (this.field == null) {
            throw new NoSuchFieldError();
        }
        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }
    }

    private Field lookupField(JvmClass c, String name, String descriptor) {
        for (Field field : c.getFields()) {
            if (field.getName().equals(name) && field.getDescriptor().equals(descriptor)) {
                return field;
            }
        }
        for (JvmClass inter : c.getInterfaces()) {
            Field field = lookupField(inter, name, descriptor);
            if (field != null) {
                return field;
            }
        }
        if (c.getSuperClass() != null) {
            return lookupField(c.getSuperClass(), name, descriptor);
        }
        return null;
    }


}
