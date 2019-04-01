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

    private boolean initStarted;

    public JvmClass(ClassFile classFile, ClassLoader classLoader) {
        this.loader = classLoader;
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        this.constantPool = new JvmConstantPool(this, classFile.getConstantPool());
        this.fields = Field.newFields(this, classFile.getFields());
        this.methods = Method.newMethods(this, classFile.getMethods());
    }

    public void startInit() {
        this.initStarted = true;
    }

    public Method getMainMethod() {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    public Method getClinitMethod() {
        return this.getStaticMethod("<clinit>", "()V");
    }

    private Method getStaticMethod(String name, String descriptor) {
        for (Method method : this.methods) {
            if (method.isStatic() && name.equals(method.getName()) &&
                    descriptor.equals(method.getDescriptor())) {
                return method;
            }
        }
        return null;
    }

    public JvmObject newObject() {
        return new JvmObject(this);
    }

    public String getPackageName() {
        int i = this.name.lastIndexOf('/');
        if (i >= 0) {
            return this.name.substring(0, i);
        }
        return "";
    }

    public boolean isSubClassOf(JvmClass other) {
        for (JvmClass c = this.superClass; c != null; c = c.getSuperClass()) {
            if (c == other) {
                return true;
            }
        }
        return false;
    }

    public boolean isSuperClassOf(JvmClass other) {
        return other.isSuperClassOf(this);
    }

    boolean isAssignableFrom(JvmClass other) {
        if (other == this) {
            return true;
        }
        if (!this.isInterface()) {
            return other.isSubClassOf(this);
        } else {
            return other.isImplements(other);
        }
    }

    public boolean isImplements(JvmClass inter) {
        for (JvmClass c = this; c != null; c = c.getSuperClass()) {
            for (JvmClass i : c.getInterfaces()) {
                if (i == inter || i.isSubInterfaceOf(inter)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSubInterfaceOf(JvmClass inter) {
        for (JvmClass superInter : this.interfaces) {
            if (superInter == inter || superInter.isSubInterfaceOf(inter)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAccessibleTo(JvmClass other) {
        return this.isPublic() || this.getPackageName().equals(other.getPackageName());
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


}
