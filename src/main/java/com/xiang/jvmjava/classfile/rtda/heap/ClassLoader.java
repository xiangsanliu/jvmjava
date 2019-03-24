package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.ClassFile;
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
        return defineClass(data);
    }

    private JvmClass defineClass(byte[] data) {
        ClassFile classFile = ClassFile.parse(data);
        return JvmClass.newClass(classFile);
    }


}
