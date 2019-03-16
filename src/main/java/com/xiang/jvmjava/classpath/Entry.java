package com.xiang.jvmjava.classpath;


import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/15 12:26
 * @comment
 */

abstract class Entry {

    abstract byte[] readClass(String className) throws IOException;


    static Entry newEntry(String path) {
        if (path.contains(";")) {
            return new EntryComposite(path);
        }
        if (StringUtils.endsWith(path, "*")) {
            return new EntryWildCard(path);
        }
        if (StringUtils.endsWithAny(path, "jar", "JAR", "zip", "ZIP")) {
            return new EntryZip(path);
        }
        return new EntryDir(path);
    }


}
