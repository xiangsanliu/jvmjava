package com.xiang.jvmjava;

import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.classpath.Classpath;
import com.xiang.jvmjava.instruction.Interpreter;

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
        ClassLoader classLoader = new ClassLoader(classpath);
        try {
            JvmClass mainClass = classLoader.loadClass(className);
            Method mainMethod = mainClass.getMainMethod();
            if (mainMethod != null) {
                Interpreter.interpret(mainMethod, cmd.getArgs());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
