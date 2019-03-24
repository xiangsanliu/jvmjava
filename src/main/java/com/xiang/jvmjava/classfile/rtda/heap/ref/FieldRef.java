package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantFieldRefInfo;
import com.xiang.jvmjava.classfile.rtda.heap.Field;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;

/**
 * @author 项三六
 * @time 2019/3/24 20:05
 * @comment
 */

public class FieldRef extends MemberRef {

    private Field field;

    public FieldRef newFieldRef(JvmConstantPool constantPool, ConstantFieldRefInfo info) {
        FieldRef ref = new FieldRef();
        ref.setConstantPool(constantPool);
        ref.copyMemberRefInfo(info);
        return ref;
    }


}
