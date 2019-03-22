package com.xiang.jvmjava;

import com.xiang.jvmjava.classfile.ClassFile;
import com.xiang.jvmjava.classfile.MemberInfo;
import com.xiang.jvmjava.classpath.Classpath;
import com.xiang.jvmjava.instruction.Interpreter;

import java.io.IOException;
import java.util.Arrays;

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
            MemberInfo memberInfo = getMainMethod(classFile);
            if (memberInfo != null) {
                Interpreter.interpret(memberInfo);
            }
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

    private static MemberInfo getMainMethod(ClassFile classFile) {
        for (MemberInfo memberInfo : classFile.getMethods()) {
            if ("main".equals(memberInfo.getName()) && "([Ljava/lang/String;)V".equals(memberInfo.getDescriptor())) {
                return memberInfo;
            }
        }
        return null;
    }

    private static void printClassInfo(ClassFile classFile) {
        System.out.printf("version: %d.%d \n", classFile.getMajorVersion(), classFile.getMinorVersion());
        System.out.println("accessFlag: " + Integer.toHexString(classFile.getAccessFlags()));
        System.out.println(classFile.getClassName());
        System.out.println(classFile.getSuperClassName());
        System.out.println(Arrays.toString(classFile.getInterfaceNames()));
        for (MemberInfo field : classFile.getFields()) {
            System.out.println(field.getName());
        }
        for (MemberInfo field : classFile.getMethods()) {
            System.out.println(field.getName());
        }


    }

}
