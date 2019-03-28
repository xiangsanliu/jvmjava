package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.ClassFile;
import com.xiang.jvmjava.classfile.rtda.Slots;
import lombok.Getter;
import lombok.Setter;


/**
 * @author 项三六
 * @time 2019/3/23 16:55
 * @comment
 */

@Getter
@Setter
public class JvmClass {

    private int accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private JvmConstantPool constantPool;
    private Field[] fields;
    private Method[] methods;
    private ClassLoader loader;
    private JvmClass superClass;
    private JvmClass[] interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Slots staticVars;

    public static JvmClass newClass(ClassFile classFile) {
        JvmClass clazz = new JvmClass();
        clazz.accessFlags = classFile.getAccessFlags();
        clazz.name = classFile.getClassName();
        clazz.superClassName = classFile.getSuperClassName();
        clazz.interfaceNames = classFile.getInterfaceNames();
//        clazz.constantPool =
//        clazz.fields =
//        clazz.methods =
        return clazz;
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean isAnnotation() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlags.ACC_ENUM);
    }

    public String getPackageName() {
        int i = this.name.lastIndexOf('/');
        if (i >= 0) {
            return this.name.substring(0, i);
        }
        return "";
    }

    public boolean isAccessibleTo(JvmClass other) {
        return this.isPublic() || this.getPackageName().equals(other.getPackageName());
    }

    public boolean isSubClassOf(JvmClass other) {
        for (JvmClass c = this.superClass; c != null; c = c.getSuperClass()) {
            if (c == other) {
                return true;
            }
        }
        return false;
    }

}
