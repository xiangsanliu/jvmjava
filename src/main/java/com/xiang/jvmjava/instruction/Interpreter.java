package com.xiang.jvmjava.instruction;

import com.xiang.jvmjava.classfile.MemberInfo;
import com.xiang.jvmjava.classfile.attribute.CodeAttribute;
import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.Thread;
import com.xiang.jvmjava.classfile.rtda.heap.Method;
import com.xiang.jvmjava.instruction.base.Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/22 16:13
 * @comment
 */

public class Interpreter {

    public static void interpret(MemberInfo memberInfo) {
        CodeAttribute codeAttr = memberInfo.getCodeAttribute();
        int maxLocals = codeAttr.getMaxLocals();
        int maxStack = codeAttr.getMaxStack();
        byte[] bytecode = codeAttr.getCode();
        Thread thread = Thread.newThread();
        Frame frame = thread.newFrame(maxLocals, maxStack);
        thread.pushFrame(frame);
        try {
            loop(thread, true);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            System.out.println(frame.getOperandStack());
            System.out.println(frame.getLocalVars());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void interpret(Method method, boolean log) throws IOException {
        Thread thread = Thread.newThread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        loop(thread, log);
    }

    private static void loop(Thread thread, boolean log) throws IOException {
        BytecodeReader reader = new BytecodeReader();
        while (!thread.isStackEmpty()) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPC();
            thread.setPc(pc);
            reader.reset(frame.getMethod().getCode(), pc);
            Instruction instruction = Instruction.newInstruction(reader.readUint8());
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            if (log) {
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
        System.out.println(String.format("%s.%s() #%2d %s", className, methodName, pc, instruction));
    }

}