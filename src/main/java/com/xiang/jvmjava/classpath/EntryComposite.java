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

    List<Entry> entries;

    EntryComposite() {
    }

    EntryComposite(String pathList) {
        entries = new ArrayList<>();
        Arrays.asList(pathList.split(";")).forEach(e -> entries.add(Entry.genEntry(e)));
    }

    @Override
    byte[] readClass(String className) throws IOException {
        for (Entry entry : entries) {
            byte[] data = entry.readClass(className);
            if (data != null) {
                return data;
            }
        }
        return null;
    }
}
