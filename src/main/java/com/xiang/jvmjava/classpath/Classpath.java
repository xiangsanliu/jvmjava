package com.xiang.jvmjava.classpath;

import com.xiang.jvmjava.Cmd;
import com.xiang.jvmjava.util.Pair;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/15 12:31
 * @comment
 */

public class Classpath {

    private Entry userClasspath;

    private Entry bootClasspath;

    private Entry extClasspath;

    public static Classpath parse(Cmd cmd) {
        Classpath classpath = new Classpath();
        classpath.parseBootAndExtClasspath();
        classpath.parseUserClasspath(cmd.getClasspath());
        return classpath;
    }

    // 双亲委派模型
    public Pair<Entry, byte[]> readClass(String className) throws IOException {
        className = StringUtils.replaceChars(className, '.', '/') + ".class";
        byte[] data = this.bootClasspath.readClass(className);
        if (data != null) {
            return new Pair<>(bootClasspath, data);
        }
        data = this.extClasspath.readClass(className);
        if (data != null) {
            return new Pair<>(extClasspath, data);
        }
        return new Pair<>(userClasspath, userClasspath.readClass(className));
    }

    private void parseUserClasspath(String classpath) {
        if (StringUtils.isEmpty(classpath)) {
            classpath = ".";
        }
        this.userClasspath = Entry.newEntry(classpath);
    }

    private void parseBootAndExtClasspath() {
        String jrePath = "/Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre";
        String jreLibPath = jrePath + File.separator + "lib" + File.separator + "*";
        this.bootClasspath = Entry.newEntry(jreLibPath);
        String extLibPath = jrePath + File.separator + "lib" + File.separator + "ext" + File.separator + "*";
        this.extClasspath = Entry.newEntry(extLibPath);
    }

}
