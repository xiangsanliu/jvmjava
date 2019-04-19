package com.xiang.jvmjava;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Thread;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.classpath.Classpath;
import com.xiang.jvmjava.instruction.Interpreter;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/4/13 14:53
 * @comment
 */

class JVM {

    private Cmd cmd;

    private ClassLoader classLoader;

    private Thread mainThread;

    JVM(Cmd cmd) {
        this.cmd = cmd;
        Classpath classpath = Classpath.parse(cmd);
        this.classLoader = new ClassLoader(classpath);
        this.mainThread = new Thread();
    }

    void start() {
        initVM();
        execMain();
    }

    private void initVM() {
        JvmClass vmClass = classLoader.loadClass("sun/misc/VM");
        Instruction.initClass(mainThread, vmClass);
        Interpreter.interpret(mainThread);
    }

    private void execMain() {
        String className = cmd.getMainClass();
        JvmClass mainClass = classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        if (mainMethod == null) {
            System.out.printf("Main method not found in class %s\n", className);
            return;
        }
        Frame frame = mainThread.newFrame(mainMethod);
        frame.getLocalVars().setRef(0, createArgsArray());
        mainThread.pushFrame(frame);
        Interpreter.interpret(mainThread);
    }

    private JvmObject createArgsArray() {
        String[] args = cmd.getArgs();
        JvmClass stringClass = classLoader.loadClass("java/lang/String");
        JvmObject argsArray = stringClass.getArrayClass().newArray(args.length);
        JvmObject[] jvmArgs = argsArray.getRefs();
        for (int i = 0; i < args.length; i++) {
            jvmArgs[i] = StringPool.getJvmString(classLoader, args[i]);
        }
        return argsArray;
    }
}
