package com.xiang.jvmjava;

import com.xiang.jvmjava.classfile.ClassFile;
import com.xiang.jvmjava.classpath.Classpath;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/15 10:49
 * @comment MainApp
 */

public class MainApp {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parseCmd(args);
        if (cmd.isHelp()) {
            cmd.printHelp();
        } else if (cmd.isVersion()) {
            cmd.printVersion();
        } else {
            run(cmd);
        }

    }

    private static void run(Cmd cmd) {
        Classpath classpath = Classpath.parse(cmd);
        String className = cmd.getMainClass();
        try {
            ClassFile classFile = loadClass(className, classpath);
            printClassInfo(classFile);
        } catch (IOException e) {
            System.out.println("Could not find or load Class: " + className);
            e.printStackTrace();
        }
    }

    private static ClassFile loadClass(String className, Classpath classpath) throws IOException {
        byte[] classData = classpath.readClass(className);
        if (null == classData) {
            return null;
        }
        return ClassFile.parse(classData);
    }

    private static void printClassInfo(ClassFile classFile) {
        System.out.printf("version: %d.%d \n", classFile.getMajorVersion(), classFile.getMinorVersion());
    }
}
