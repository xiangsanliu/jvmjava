package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.ConstantInfo;
import com.xiang.jvmjava.classfile.ConstantLiteralInfo;
import com.xiang.jvmjava.classfile.ConstantPool;
import com.xiang.jvmjava.classfile.constantinfo.*;
import com.xiang.jvmjava.classfile.rtda.heap.ref.ClassRef;
import com.xiang.jvmjava.classfile.rtda.heap.ref.FieldRef;
import com.xiang.jvmjava.classfile.rtda.heap.ref.InterfaceMethodRef;
import com.xiang.jvmjava.classfile.rtda.heap.ref.MethodRef;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/23 20:26
 * @comment
 */

public class JvmConstantPool {

    @Getter
    private JvmClass clazz;

    private Object[] consts;

    public Object getConstant(int index) {
        return this.consts[index];
    }

    public JvmConstantPool(JvmClass clazz, ConstantPool constantPool) {
        this.clazz = clazz;
        this.consts = copyConsts(constantPool);
    }

    private Object[] copyConsts(ConstantPool constantPool) {
        int count = constantPool.getConstantInfos().length;
        Object[] consts = new Object[count];
        for (int i = 1; i < count; i++) {
            ConstantInfo info = constantPool.getConstantInfos()[i];
            if (info instanceof ConstantLiteralInfo) {
                consts[i] = ((ConstantLiteralInfo) info).value();
                if (info instanceof ConstantLongInfo || info instanceof ConstantDoubleInfo) {
                    i++;
                }
            } else if (info instanceof ConstantClassInfo) {
                consts[i] = new ClassRef(this, (ConstantClassInfo) info);
            } else if (info instanceof ConstantFieldRefInfo) {
                consts[i] = new FieldRef(this, (ConstantFieldRefInfo) info);
            } else if (info instanceof ConstantMethodRefInfo) {
                consts[i] = new MethodRef(this, (ConstantMethodRefInfo) info);
            } else if (info instanceof ConstantInterfaceMethodRefInfo) {
                consts[i] = new InterfaceMethodRef(this, (ConstantInterfaceMethodRefInfo) info);
            }
        }
        return consts;
    }

}
