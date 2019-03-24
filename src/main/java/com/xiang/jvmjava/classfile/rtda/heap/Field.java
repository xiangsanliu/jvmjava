package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.MemberInfo;

/**
 * @author 项三六
 * @time 2019/3/23 17:18
 * @comment
 */

public class Field extends ClassMember {

    public static Field[] newFields(JvmClass clazz, MemberInfo[] memberInfos) {
        Field[] fields = new Field[memberInfos.length];
        for (int i = 0; i < memberInfos.length; i++) {
            fields[i] = new Field();
            fields[i].clazz = clazz;
            fields[i].copyMemberInfo(memberInfos[i]);
        }
        return fields;
    }

}
