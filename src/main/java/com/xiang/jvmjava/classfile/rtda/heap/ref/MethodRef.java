package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantMethodRefInfo;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.Method;

/**
 * @author 项三六
 * @time 2019/3/24 20:10
 * @comment
 */

public class MethodRef extends MemberRef {

    private Method method;

    public MethodRef(JvmConstantPool constantPool, ConstantMethodRefInfo info) {
        super(constantPool, info);
    }
}
