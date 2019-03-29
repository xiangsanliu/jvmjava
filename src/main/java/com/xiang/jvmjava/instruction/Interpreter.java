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
            loop(thread, bytecode);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            System.out.println(frame.getOperandStack());
            System.out.println(frame.getLocalVars());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void interpret(Method method) throws IOException {
        Thread thread = Thread.newThread();
        Frame frame = thread.newFrame(method);
        thread.pushFrame(frame);
        loop(thread, method.getCode());
    }

    public static void loop(Thread thread, byte[] bytecode) throws IOException {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();
        while (true) {
            int pc = frame.getNextPC();
            thread.setPc(pc);
            reader.reset(bytecode, pc);
            Instruction instruction = Instruction.newInstruction(reader.readUint8());
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            System.out.println(String.format("pc: %2d inst:%s", pc, instruction.toString()));
            instruction.execute(frame);
        }
    }

}
