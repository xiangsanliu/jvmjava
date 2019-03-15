package com.xiang.jvmjava.classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 项三六
 * @time 2019/3/15 14:42
 * @comment
 */

public class EntryWildCard extends EntryComposite {


    public EntryWildCard(String path) {
        String baseDir = path.substring(0, path.length() - 1);
        List<Entry> entries = new ArrayList<>();
        File dir = new File(baseDir);
        for (File e : Objects.requireNonNull(dir.listFiles())) {
            if (!e.isDirectory()) {
                String fileName = e.getName();
                String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
                if ("jar".equals(suffix)) {
                    entries.add(new EntryZip(e.getAbsolutePath()));
                }
            }
        }
        this.entries = entries;
    }

}
