package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.ConstantInfo;
import com.xiang.jvmjava.classfile.ConstantLiteralInfo;
import com.xiang.jvmjava.classfile.ConstantPool;
import com.xiang.jvmjava.classfile.constantinfo.ConstantDoubleInfo;
import com.xiang.jvmjava.classfile.constantinfo.ConstantLongInfo;
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

    public JvmConstantPool(JvmClass clazz, Object[] consts) {
        this.clazz = clazz;
        this.consts = consts;
    }

    public Object getConstant(int index) {
        Object constant = this.consts[index];
        if (constant != null) {
            return constant;
        }
        return null;
    }

    public static JvmConstantPool newConstantPool(JvmClass clazz, ConstantPool constantPool) {
        int count = constantPool.getConstantInfos().length;
        Object[] consts = new Constant[count];
        JvmConstantPool jvmConstantPool = new JvmConstantPool(clazz, consts);
        for (int i = 0; i < count; i++) {
            ConstantInfo constantInfo = constantPool.getConstantInfos()[i];
            if (constantInfo instanceof ConstantLiteralInfo) {
                consts[i] = ((ConstantLiteralInfo) constantInfo).value();
                if (constantInfo instanceof ConstantLongInfo || constantInfo instanceof ConstantDoubleInfo) {
                    i++;
                }
            } else {

            }
        }
        return jvmConstantPool;
    }

}
