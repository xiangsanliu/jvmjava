package com.xiang.jvmjava.jvmnative.java.io;

import com.xiang.jvmjava.rtda.heap.JvmObject;
import com.xiang.jvmjava.rtda.heap.StringPool;
import com.xiang.jvmjava.jvmnative.Registry;

import java.io.File;
import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/4/19 10:09
 * @comment
 */

public class UnixFileSystem {

    public static void registerNatives() {
        Registry.register("java/io/UnixFileSystem", "getBooleanAttributes0", "(Ljava/io/File;)I", frame -> {
            JvmObject fileObject = frame.getLocalVars().getRef(1);
            JvmObject pathObject = fileObject.getRefVar("path", "Ljava/lang/String;");
            File file = new File(StringPool.jvmStrToString(pathObject));

            int attrs = (file.exists() ? 0x01 : 0x00) |
                    (file.isFile() ? 0x02 : 0x00) |
                    (file.isDirectory() ? 0x04 : 0x00) |
                    (file.isHidden() ? 0x08 : 0x00);
            frame.getOperandStack().pushInt(attrs);
        });

        Registry.register("java/io/UnixFileSystem", "canonicalize0", "(Ljava/lang/String;)Ljava/lang/String;", frame -> {
            JvmObject pathObject = frame.getLocalVars().getRef(1);
            File file = new File(StringPool.jvmStrToString(pathObject));
            try {
                java.lang.String path = file.getCanonicalPath();
                frame.getOperandStack().pushRef(StringPool.getJvmString(frame.getMethod().getClazz().getLoader(), path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
