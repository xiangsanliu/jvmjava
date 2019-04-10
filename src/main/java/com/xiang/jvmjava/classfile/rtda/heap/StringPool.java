package com.xiang.jvmjava.classfile.rtda.heap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 项三六
 * @time 2019/4/5 18:37
 * @comment
 */

public class StringPool {

    private static Map<String, JvmObject> internedStrings;

    static {
        internedStrings = new HashMap<>();
    }

    public static JvmObject getJvmString(ClassLoader loader, String str) throws IOException {
        JvmObject internedString = internedStrings.get(str);
        if (internedString != null) {
            return internedString;
        }
        JvmObject chars = new JvmObject(loader.loadClass("[C"), str.toCharArray());
        JvmObject jvmStr = loader.loadClass("java/lang/String").newObject();
        jvmStr.setRefVar("value", "[C", chars);
        internedStrings.put("str", jvmStr);
        return jvmStr;
    }

    public static String jvmStrToString(JvmObject jvmStr) {
        JvmObject chars = jvmStr.getRefVar("value", "[C");
        return new String(chars.getChars());
    }

}