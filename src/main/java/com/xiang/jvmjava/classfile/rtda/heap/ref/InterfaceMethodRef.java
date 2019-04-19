package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantInterfaceMethodRefInfo;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;

/**
 * @author 项三六
 * @time 2019/3/24 20:12
 * @comment 接口方法符号引用
 */

public class InterfaceMethodRef extends MemberRef {

    private Method method;

    public InterfaceMethodRef(JvmConstantPool constantPool, ConstantInterfaceMethodRefInfo info) {
        super(constantPool, info);
    }

    public Method resolvedInterfaceMethod() {
        if (this.method == null) {
            this.resolveInterfaceMethodRef();
        }
        return this.method;
    }

    // 解析符号引用为直接引用
    private void resolveInterfaceMethodRef() {
        JvmClass c = this.resolvedClass();
        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        this.method = lookupInterfaceMethod(c, this.name, this.descriptor);
        if (method == null) {
            throw new NoSuchMethodError();
        }
        if (!method.isAccessibleTo(this.getConstantPool().getClazz())) {
            throw new IllegalAccessError();
        }
    }

    private Method lookupInterfaceMethod(JvmClass inter, String name, String descriptor) {
        for (Method method : inter.getMethods()) {
            if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }
        return lookupMethodInInterfaces(inter.getInterfaces(), name, descriptor);
    }

}
