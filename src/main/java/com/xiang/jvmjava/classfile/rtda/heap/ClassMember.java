package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.MemberInfo;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/3/23 17:14
 * @comment
 */

@Getter
public abstract class ClassMember {

    protected int accessFlags;

    protected String name;

    protected String descriptor;

    protected JvmClass clazz;

    void copyMemberInfo(MemberInfo info) {
        this.accessFlags = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
    }

    abstract void copyAttributes(MemberInfo info);

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isStatic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    // 当前类成员对于类D是否是可访问的
    public boolean isAccessibleTo(JvmClass d) {
        if (this.isPublic()) {
            return true;
        }
        JvmClass c = this.getClazz();
        if (this.isProtected()) {
            return d == c || d.isSubClassOf(c) || c.getPackageName().equals(d.getPackageName());
        }
        if (!this.isPrivate()) {
            return c.getPackageName().equals(d.getPackageName());
        }
        return d == c;
    }
}
