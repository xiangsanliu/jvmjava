package com.xiang.jvmjava.rtda.heap;

import com.xiang.jvmjava.Cmd;
import com.xiang.jvmjava.classfile.ClassFile;
import com.xiang.jvmjava.rtda.Slots;
import com.xiang.jvmjava.rtda.heap.member.Field;
import com.xiang.jvmjava.classpath.Classpath;
import com.xiang.jvmjava.classpath.Entry;
import com.xiang.jvmjava.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 项三六
 * @time 2019/3/24 20:22
 * @comment
 */

@Getter
@Setter
public class ClassLoader {

    private Classpath classpath;

    private Map<String, JvmClass> classMap;

    public ClassLoader(Classpath classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
        this.loadBasicClasses();
        this.loadPrimitiveClasses();
    }


    public JvmClass loadClass(String name) {
        JvmClass clazz = this.classMap.get(name);
        if (clazz != null) {
            return clazz;
        }
        if (name.charAt(0) == '[') {
            clazz = loadArrayClass(name);
        } else {
            clazz = loadNonArrayClass(name);
        }
        JvmClass classClass = this.classMap.get("java/lang/Class");
        if (classClass != null) {
            clazz.setJvmClass(classClass.newObject());
            clazz.getJvmClass().setExtra(clazz);
        }
        return clazz;
    }

    private JvmClass loadNonArrayClass(String name) {
        Pair<Entry, byte[]> result;
        try {
            result = this.classpath.readClass(name);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("java.lang.ClassNotFoundException: " + name);
        }
        JvmClass clazz = defineClass(result.getValue());
        link(clazz);
        if (Cmd.logClassLoader) {
            System.out.printf("[Loaded '%s' from '%s']\n", name, result.getKey());
        }
        return clazz;
    }

    private JvmClass loadArrayClass(String name) {
        JvmClass clazz = new JvmClass();
        clazz.setAccessFlags(AccessFlags.ACC_PUBLIC);
        clazz.setName(name);
        clazz.setLoader(this);
        clazz.setInitStarted(true);
        clazz.setSuperClass(this.loadClass("java/lang/Object"));
        clazz.setInterfaces(new JvmClass[]{
                this.loadClass("java/lang/Cloneable"),
                this.loadClass("java/io/Serializable")});
        this.classMap.put(name, clazz);
        if (Cmd.logClassLoader) {
            System.out.printf("[Loaded '%s' ]\n", name);
        }
        return clazz;
    }

    private JvmClass defineClass(byte[] data) {
        ClassFile classFile = ClassFile.parse(data);
        JvmClass clazz = new JvmClass(classFile, this);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        this.classMap.put(clazz.getName(), clazz);
        return clazz;
    }

    private void resolveSuperClass(JvmClass clazz) {
        if (!"java/lang/Object".equals(clazz.getName())) {
            clazz.setSuperClass(clazz.getLoader().loadClass(clazz.getSuperClassName()));
        }
    }

    private void resolveInterfaces(JvmClass clazz) {
        String[] interfaceNames = clazz.getInterfaceNames();
        int count = interfaceNames.length;
        JvmClass[] interfaces = new JvmClass[count];
        for (int i = 0; i < count; i++) {
            interfaces[i] = clazz.getLoader().loadClass(interfaceNames[i]);
        }
        clazz.setInterfaces(interfaces);
    }

    private void link(JvmClass clazz) {
//        verify
        prepare(clazz);
    }

    private static void prepare(JvmClass clazz) {
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    private static void calcInstanceFieldSlotIds(JvmClass clazz) {
        int slotId = 0;
        if (clazz.getSuperClass() != null) {
            slotId = clazz.getSuperClass().getInstanceSlotCount();
        }
        for (Field field : clazz.getFields()) {
            if (!field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.setInstanceSlotCount(slotId);
    }

    private static void calcStaticFieldSlotIds(JvmClass clazz) {
        int slotId = 0;
        for (Field field : clazz.getFields()) {
            if (field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.setStaticSlotCount(slotId);
    }

    private static void allocAndInitStaticVars(JvmClass clazz) {
        clazz.setStaticVars(new Slots(clazz.getStaticSlotCount()));
        for (Field field : clazz.getFields()) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    private static void initStaticFinalVar(JvmClass clazz, Field field) {
        Slots vars = clazz.getStaticVars();
        JvmConstantPool constantPool = clazz.getConstantPool();
        int index = field.getConstantValueIndex();
        if (index > 0) {
            switch (field.getDescriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    vars.setInt(field.getSlotId(), (Integer) constantPool.getConstant(index));
                    break;
                case "J":
                    vars.setLong(field.getSlotId(), (Long) constantPool.getConstant(index));
                    break;
                case "F":
                    vars.setFloat(field.getSlotId(), (Float) constantPool.getConstant(index));
                    break;
                case "D":
                    vars.setDouble(field.getSlotId(), (Double) constantPool.getConstant(index));
                    break;
                case "Ljava/lang/String;":
                    String str = (String) constantPool.getConstant(index);
                    JvmObject jvmStr = StringPool.getJvmString(clazz.getLoader(), str);
                    vars.setRef(field.getSlotId(), jvmStr);
            }
        }
    }

    private void loadBasicClasses() {
        JvmClass jvmClass = this.loadClass("java/lang/Class");
        for (Map.Entry<String, JvmClass> entry : this.classMap.entrySet()) {
            if (entry.getValue().getJvmClass() == null) {
                entry.getValue().setJvmClass(jvmClass.newObject());
                entry.getValue().getJvmClass().setExtra(entry.getValue());
            }
        }
    }

    private void loadPrimitiveClasses() {
        for (Map.Entry<String, String> entry : JvmClass.primitiveTypes.entrySet()) {
            this.loadPrimitiveClass(entry.getKey());
        }
    }

    private void loadPrimitiveClass(String className) {
        JvmClass clazz = new JvmClass();
        clazz.setAccessFlags(AccessFlags.ACC_PUBLIC);
        clazz.setName(className);
        clazz.setLoader(this);
        clazz.setInitStarted(true);
        clazz.setJvmClass(this.classMap.get("java/lang/Class").newObject());
        clazz.getJvmClass().setExtra(clazz);
        this.classMap.put(className, clazz);
    }

}
