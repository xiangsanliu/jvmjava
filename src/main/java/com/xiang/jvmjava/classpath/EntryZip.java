package com.xiang.jvmjava.classpath;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author 项三六
 * @time 2019/3/15 13:55
 * @comment
 */

public class EntryZip extends Entry {

    private String absPath;


    public EntryZip(String absPath) {
        this.absPath = absPath;
    }

    @Override
    byte[] readClass(String className) throws IOException {
        ZipFile zipFile = new ZipFile(absPath);
        ZipInputStream inputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(absPath)));
        ZipEntry zipEntry;
        while ((zipEntry = inputStream.getNextEntry()) != null) {
            if (className.equals(zipEntry.getName())) {
                return IOUtils.toByteArray(zipFile.getInputStream(zipEntry));
            }
        }
        return null;
    }

}
