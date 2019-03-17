package com.xiang.jvmjava;

import com.xiang.jvmjava.classfile.ClassFile;
import com.xiang.jvmjava.classfile.MemberInfo;
import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.LocalVars;
import com.xiang.jvmjava.classfile.rtda.OperandStack;
import com.xiang.jvmjava.classpath.Classpath;

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
//            run(cmd);
            Frame frame = Frame.newFrame(100, 100);
            testLocalVars(frame.getLocalVars());
            testOperandStack(frame.getOperandStack());
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

    private static void testLocalVars(LocalVars vars) {
        vars.setInt(0, 100);
        vars.setInt(1, -100);
        vars.setLong(2, 2997924580L);
        vars.setLong(4, -2997924580L);
        vars.setFloat(6, 3.1415926f);
        vars.setDouble(7, 2.71828182845f);
        vars.setRef(9, null);
        System.out.println(vars.getInt(0));
        System.out.println(vars.getInt(1));
        System.out.println(vars.getLong(2));
        System.out.println(vars.getLong(4));
        System.out.println(vars.getFloat(6));
        System.out.println(vars.getDouble(7));
        System.out.println(vars.getRef(9));
    }

    private static void testOperandStack(OperandStack ops) {
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushLong(2997924580L);
        ops.pushLong(-2997924580L);
        ops.pushFloat(3.1415926F);
        ops.pushDouble(2.71828182845D);
        ops.pushRef(null);
        System.out.println(ops.popRef());
        System.out.println(ops.popDouble());
        System.out.println(ops.popFloat());
        System.out.println(ops.popLong());
        System.out.println(ops.popLong());
        System.out.println(ops.popInt());
        System.out.println(ops.popInt());
    }

}
