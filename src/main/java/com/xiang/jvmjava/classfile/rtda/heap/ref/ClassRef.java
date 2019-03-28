package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantClassInfo;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;

/**
 * @author 项三六
 * @time 2019/3/23 21:39
 * @comment
 */

public class ClassRef extends SymRef {

    public static ClassRef newClassRef(JvmConstantPool constantPool, ConstantClassInfo info) {
        ClassRef ref = new ClassRef();
        ref.setConstantPool(constantPool);
        ref.setClassName(info.getName());
        return ref;
    }

}
