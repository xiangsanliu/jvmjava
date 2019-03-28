package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

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

    public JvmClass resolvedClass() throws IOException {
        if (this.clazz == null) {
            this.resolveClassRef();
        }
        return this.clazz;
    }

    private void resolveClassRef() throws IOException {
        JvmClass d = this.constantPool.getClazz();
        JvmClass c = clazz.getLoader().loadClass(this.className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }
        this.clazz = c;
    }


}
