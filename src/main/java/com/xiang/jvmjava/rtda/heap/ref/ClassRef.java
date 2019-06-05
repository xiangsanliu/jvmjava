package com.xiang.jvmjava.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantClassInfo;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;

/**
 * @author 项三六
 * @time 2019/3/23 21:39
 * @comment
 */

public class ClassRef extends SymRef {

    public ClassRef(JvmConstantPool constantPool, ConstantClassInfo info) {
        this.constantPool = constantPool;
        this.className = info.getName();
    }

}
