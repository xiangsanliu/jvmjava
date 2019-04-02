package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.Cmd;
import com.xiang.jvmjava.classfile.ClassFile;
import com.xiang.jvmjava.classfile.rtda.Slots;
import com.xiang.jvmjava.classfile.rtda.heap.member.Field;
import com.xiang.jvmjava.classpath.Classpath;
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
    }

    public JvmClass loadClass(String name) throws IOException {
        JvmClass clazz = this.classMap.get(name);
        if (clazz != null) {
            return clazz;
        }
        return loadNonArrayClass(name);
    }

    private JvmClass loadNonArrayClass(String name) throws IOException {
        byte[] data = this.classpath.readClass(name);
        JvmClass clazz = defineClass(data);
        link(clazz);
        if (Cmd.log) {
            System.out.printf("[Loaded %s]\n", name);
        }
        return clazz;
    }

    private JvmClass defineClass(byte[] data) throws IOException {
        ClassFile classFile = ClassFile.parse(data);
        JvmClass clazz = new JvmClass(classFile, this);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        this.classMap.put(clazz.getName(), clazz);
        return clazz;
    }

    private void resolveSuperClass(JvmClass clazz) throws IOException {
        if (!"java/lang/Object".equals(clazz.getName())) {
            clazz.setSuperClass(clazz.getLoader().loadClass(clazz.getSuperClassName()));
        }
    }

    private void resolveInterfaces(JvmClass clazz) throws IOException {
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
                    throw new Error("todo");
            }
        }
    }


}
