package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.ClassFile;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.member.Field;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 项三六
 * @time 2019/3/23 16:55
 * @comment
 */

@Getter
@Setter
@NoArgsConstructor
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

    private JvmObject jvmClass;

    public static Map<String, String> primitiveTypes = new HashMap<>();

    static {
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

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

    Field getField(String name, String descriptor, boolean isStatic) {
        for (JvmClass c = this; c != null; c = c.getSuperClass()) {
            for (Field field : this.fields) {
                if (field.isStatic() == isStatic && field.getName().equals(name) && field.getDescriptor().equals(descriptor)) {
                    return field;
                }
            }
        }
        return null;
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

    private JvmObject newObject(Object data) {
        JvmObject object = new JvmObject(this);
        object.setData(data);
        return object;
    }

    public JvmObject newArray(int count) {
        if (!isArray()) {
            throw new Error("Not array class: " + this.name);
        }
        switch (this.name) {
            case "[Z":
            case "[B":
                return newObject(new byte[count]);
            case "[C":
            case "[S":
                return newObject(new short[count]);
            case "[I":
                return newObject(new int[count]);
            case "[J":
                return newObject(new long[count]);
            case "[F":
                return newObject(new float[count]);
            case "[D":
                return newObject(new double[count]);
            default:
                JvmObject[] objects = new JvmObject[count];
                for (int i = 0; i < count; i++) {
                    objects[i] = new JvmObject();
                }
                return newObject(objects);
        }
    }

    public JvmClass getElementClass() throws IOException {
        String name = getElementClassName(this.name);
        return this.loader.loadClass(name);
    }

    public JvmClass getArrayClass() throws IOException {
        return this.loader.loadClass(getArrayClassName(this.name));
    }

    private String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    private String getElementClassName(String name) {
        if (name.charAt(0) == '[') {
            String descriptor = name.substring(1);
            return toClassName(descriptor);
        }
        throw new Error("Not array: " + name);
    }

    private String toClassName(String descriptor) {
        if (descriptor.charAt(0) == '[') {
            return descriptor;
        }
        if (descriptor.charAt(0) == 'L') {
            return descriptor.substring(1, descriptor.length() - 1);
        }
        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (entry.getValue().equals(descriptor)) {
                return entry.getKey();
            }
        }
        throw new Error("Invalid descriptor: " + descriptor);
    }


    private String toDescriptor(String className) {
        if (className.charAt(0) == '[') {
            return className;
        }
        String name = primitiveTypes.get(className);
        if (name != null) {
            return name;
        }
        return "L" + className + ";";
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

    boolean isAssignableFrom(JvmClass other) throws IOException {
        JvmClass t = this;
        if (other == t) {
            return true;
        }
        if (!other.isArray()) {
            if (!other.isInterface()) {
                // s 是 class
                if (!t.isInterface()) {
                    // t 不是接口
                    return other.isSubClassOf(t);
                } else {
                    // t 是接口
                    return other.isImplements(t);
                }
            } else {
                if (!t.isInterface()) {
                    return t.isJ1Object();
                } else {
                    return t.isSuperClassOf(other);
                }
            }
        } else {
            if (!t.isArray()) {
                if (!t.isInterface()) {
                    return t.isJ1Object();
                } else {
                    return t.isJlCloneable() || t.isJioSerializable();
                }
            } else {
                JvmClass se = other.getElementClass();
                JvmClass te = t.getElementClass();
                return se == te || te.isAssignableFrom(se);
            }
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

    private boolean isJ1Object() {
        return "java/lang/Object".equals(this.name);
    }

    private boolean isJlCloneable() {
        return "java/lang/Cloneable".equals(this.name);
    }

    private boolean isJioSerializable() {
        return "java/io/Serializable".equals(this.name);
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

    private boolean isArray() {
        return this.name.charAt(0) == '[';
    }

}
