package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/23 21:35
 * @comment
 */
@Getter
@Setter
public abstract class SymRef {


    private JvmConstantPool constantPool;

    private String className;

    private JvmClass clazz;

    public JvmClass resolvedClass() {
        return this.clazz;
    }


}
