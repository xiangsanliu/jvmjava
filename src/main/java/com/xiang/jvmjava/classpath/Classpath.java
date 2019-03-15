package com.xiang.jvmjava.classpath;

import com.xiang.jvmjava.Cmd;
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

    public byte[] readClass(String className) throws IOException {
        className = StringUtils.replaceChars(className, '.', '/') + ".class";
        byte[] data = this.bootClasspath.readClass(className);
        if (data != null) {
            return data;
        }
        data = this.extClasspath.readClass(className);
        if (data != null) {
            return data;
        }
        return userClasspath.readClass(className);
    }

    private void parseUserClasspath(String classpath) {
        if (StringUtils.isEmpty(classpath)) {
            classpath = ".";
        }
        this.userClasspath = Entry.genEntry(classpath);
    }

    private void parseBootAndExtClasspath() {
        String jrePath = System.getenv("JAVA_HOME") + "jre";
        String jreLibPath = jrePath + File.separator + "lib" + File.separator + "*";
        this.bootClasspath = Entry.genEntry(jreLibPath);
        String extLibPath = jrePath + File.separator + "lib" + File.separator + "ext" + File.separator + "*";
        this.extClasspath = Entry.genEntry(extLibPath);
    }

    public static void main(String[] args) {
        new Classpath().parseBootAndExtClasspath();
    }


}
