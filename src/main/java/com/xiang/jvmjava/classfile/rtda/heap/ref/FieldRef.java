package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantMemberRefInfo;
import com.xiang.jvmjava.classfile.rtda.heap.member.Field;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;

import java.io.IOException;

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

    public Field resolvedField() throws IOException {
        if (this.field == null) {
            resolveFieldRef();
        }
        return this.field;
    }

    private void resolveFieldRef() throws IOException {
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
