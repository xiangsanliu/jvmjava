package com.xiang.jvmjava.rtda.heap.ref;

import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/23 21:35
 * @comment
 */
@Getter
@Setter
public class SymRef {

    protected JvmConstantPool constantPool;

    protected String className;

    private JvmClass clazz;

    public JvmClass resolvedClass() {
        if (this.clazz == null) {
            this.resolveClassRef();
        }
        return this.clazz;
    }

    // 解析符号引用为直接引用
    private void resolveClassRef() {
        JvmClass d = this.constantPool.getClazz();
        JvmClass c = d.getLoader().loadClass(this.className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }
        this.clazz = c;
    }


}
