package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantInterfaceMethodRefInfo;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.Method;

/**
 * @author 项三六
 * @time 2019/3/24 20:12
 * @comment
 */

public class InterfaceMethodRef extends MemberRef {

    private Method method;

    public InterfaceMethodRef(JvmConstantPool constantPool, ConstantInterfaceMethodRefInfo info) {
        super(constantPool, info);
    }
}
