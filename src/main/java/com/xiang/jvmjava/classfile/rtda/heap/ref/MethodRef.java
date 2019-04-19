package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantMethodRefInfo;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;

/**
 * @author 项三六
 * @time 2019/3/24 20:10
 * @comment 非接口方法符号引用
 */

public class MethodRef extends MemberRef {

    private Method method;

    public MethodRef(JvmConstantPool constantPool, ConstantMethodRefInfo info) {
        super(constantPool, info);
    }

    public Method resolvedMethod() {
        if (this.method == null) {
            this.resolveMethodRef();
        }
        return this.method;
    }

    // 解析符号引用为直接引用
    private void resolveMethodRef() {
        JvmClass c = this.resolvedClass();
        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        this.method = lookupMethod(c, this.name, this.descriptor);
        if (method == null) {
            throw new NoSuchMethodError();
        }
        if (!method.isAccessibleTo(this.constantPool.getClazz())) {
            throw new IllegalAccessError();
        }
    }

    private Method lookupMethod(JvmClass clazz, String name, String descriptor) {
        Method method = lookupMethodInClass(clazz, name, descriptor);
        if (method == null) {
            method = lookupMethodInInterfaces(clazz.getInterfaces(), name, descriptor);
        }
        return method;
    }

    // 从类中查找方法
    public static Method lookupMethodInClass(JvmClass clazz, String name, String descriptor) {
        for (JvmClass c = clazz; c != null; c = c.getSuperClass()) {
            for (Method method : c.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }
}
