package com.xiang.jvmjava.util;

import com.xiang.jvmjava.rtda.heap.ClassLoader;
import com.xiang.jvmjava.rtda.heap.JvmClass;
import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.StringPool;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 项三六
 * @time 2019/4/16 17:10
 * @comment
 */

public class JvmClassHelper {

    public static JvmObject getSignature(ClassLoader classLoader, String signature) {
        if (StringUtils.isNotEmpty(signature)) {
            return StringPool.getJvmString(classLoader, signature);
        }
        return null;
    }

    public static JvmObject toJvmByteArray(ClassLoader classLoader, byte[] bytes) {
        if (bytes != null) {
            return JvmClass.newJvmByteArray(classLoader, bytes);
        }
        return null;
    }

    public static JvmObject toClassArray(ClassLoader loader, JvmClass[] classes) {
        if (classes == null) {
            return null;
        }
        JvmObject classArray = loader.loadClass("java/lang/Class").getArrayClass().newArray(classes.length);
        JvmObject[] classObjs = classArray.getRefs();
        for (int i = 0; i < classes.length; i++) {
            classObjs[i] = classes[i].getJvmClass();
        }
        return classArray;
    }

}
