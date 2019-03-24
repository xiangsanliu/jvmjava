package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.MemberInfo;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/23 17:14
 * @comment
 */

@Getter
public class ClassMember {

    private int accessFlags;

    private String name;

    private String descriptor;

    protected JvmClass clazz;

    void copyMemberInfo(MemberInfo memberInfo) {
        this.accessFlags = memberInfo.getAccessFlags();
        this.name = memberInfo.getName();
        this.descriptor = memberInfo.getDescriptor();
    }

}
