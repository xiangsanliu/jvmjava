package com.xiang.jvmjava.classfile.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantMemberRefInfo;
import com.xiang.jvmjava.util.Pair;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/24 16:00
 * @comment
 */

@Getter
@Setter
public class MemberRef extends SymRef {

    private String name;

    private String descriptor;

    void copyMemberRefInfo(ConstantMemberRefInfo info) {
        this.setClassName(info.className());
        Pair<String, String> pair = info.nameAndDescriptor();
        this.setName(pair.getKey());
        this.setDescriptor(pair.getValue());
    }



}
