package com.xiang.jvmjava.classpath;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/15 12:28
 * @comment
 */

public class EntryDir extends Entry {

    private String absDir;

    EntryDir(String absDir) {
        this.absDir = absDir;
    }

    @Override
    byte[] readClass(String className) throws IOException {
        String fileName = this.absDir + className;
        return FileUtils.readFileToByteArray(new File(fileName));
    }

    @Override
    public String toString() {
        return this.absDir;
    }
}
