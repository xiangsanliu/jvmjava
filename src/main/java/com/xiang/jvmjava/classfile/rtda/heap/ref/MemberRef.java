package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantMemberRefInfo;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.util.Pair;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/24 16:00
 * @comment
 */

@Getter
public class MemberRef extends SymRef {

    protected String name;

    protected String descriptor;

    MemberRef(JvmConstantPool constantPool, ConstantMemberRefInfo info) {
        this.constantPool = constantPool;
        this.copyMemberRefInfo(info);
    }

    private void copyMemberRefInfo(ConstantMemberRefInfo info) {
        this.setClassName(info.className());
        Pair<String, String> pair = info.nameAndDescriptor();
        this.name = pair.getKey();
        this.descriptor = pair.getValue();
    }


}
