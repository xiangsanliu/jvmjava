package com.xiang.jvmjava.classpath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/15 14:29
 * @comment
 */

class EntryComposite extends Entry {

    private String path;

    List<Entry> entries;

    EntryComposite() {
    }

    EntryComposite(String pathList) {
        entries = new ArrayList<>();
        Arrays.asList(pathList.split(";")).forEach(e -> entries.add(Entry.newEntry(e)));
    }

    @Override
    byte[] readClass(String className) throws IOException {
        for (Entry entry : entries) {
            byte[] data = entry.readClass(className);
            if (data != null) {
                this.path = entry.toString();
                return data;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.path;
    }
}
