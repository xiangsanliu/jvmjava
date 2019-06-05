package com.xiang.jvmjava.rtda.heap.ref;

import com.xiang.jvmjava.classfile.constantinfo.ConstantMemberRefInfo;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.rtda.heap.member.Method;
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

    // 从接口中查找方法
    Method lookupMethodInInterfaces(JvmClass[] inters, String name, String descriptor) {
        for (JvmClass inter : inters) {
            for (Method method : inter.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
            // 从父接口查找方法
            Method method = lookupMethodInInterfaces(inter.getInterfaces(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }


}
