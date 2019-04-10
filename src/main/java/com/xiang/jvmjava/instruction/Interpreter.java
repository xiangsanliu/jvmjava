package com.xiang.jvmjava.instruction;

import com.xiang.jvmjava.Cmd;
import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Thread;
import com.xiang.jvmjava.classfile.rtda.heap.ClassLoader;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.StringPool;
import com.xiang.jvmjava.classfile.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/22 16:13
 * @comment
 */

public class Interpreter {

    public static void interpret(Method method, String[] args) throws IOException {
        Thread thread = new Thread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        frame.getLocalVars().setRef(0, createArgsArray(method.getClazz().getLoader(), args));
        loop(thread);
    }

    private static void loop(Thread thread) throws IOException {
        BytecodeReader reader = new BytecodeReader();
        while (!thread.isStackEmpty()) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPC();
            thread.setPc(pc);
            reader.reset(frame.getMethod().getCode(), pc);
            Instruction instruction = InstructionFactory.newInstruction(reader.readUint8());
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            if (Cmd.logInstruction) {
                logInstruction(frame, instruction);
            }
            instruction.execute(frame);
        }
    }

    private static void logInstruction(Frame frame, Instruction instruction) {
        Method method = frame.getMethod();
        String className = method.getClazz().getName();
        String methodName = method.getName();
        int pc = frame.getThread().getPc();
        System.out.printf("%s.%s() #pc: %2d %s\n", className, methodName, pc, instruction);
    }

    private static JvmObject createArgsArray(ClassLoader loader, String[] args) throws IOException {
        JvmClass stringClass = loader.loadClass("java/lang/String");
        JvmObject argsArray = stringClass.getArrayClass().newArray(args.length);
        JvmObject[] jvmArgs = argsArray.getRefs();
        for (int i = 0; i < args.length; i++) {
            jvmArgs[i] = StringPool.getJvmString(loader, args[i]);
        }
        return argsArray;
    }

}
