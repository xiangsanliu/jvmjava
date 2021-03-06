package com.xiang.jvmjava.instruction;

import com.xiang.jvmjava.Cmd;
import com.xiang.jvmjava.rtda.Frame;
import com.xiang.jvmjava.rtda.Thread;
import com.xiang.jvmjava.rtda.heap.member.Method;
import com.xiang.jvmjava.instruction.base.Instruction;

/**
 * @author 项三六
 * @time 2019/3/22 16:13
 * @comment
 */

public class Interpreter {

    public static void interpret(Thread thread) {
        BytecodeReader reader = new BytecodeReader();
        while (!thread.isStackEmpty()) {
            Frame frame = thread.currentFrame();
            int pc = frame.getNextPC();
            thread.setPC(pc);
            reader.reset(frame.getMethod().getCode(), pc);
            Instruction instruction = InstructionFactory.newInstruction(reader.readUint8());
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPC());
            if (Cmd.logInstruction) {
                logInstruction(frame, instruction, false);
            }
            instruction.execute(frame);
        }
    }

    private static void logInstruction(Frame frame, Instruction instruction, boolean mainOnly) {
        if (mainOnly && !"main".equals(frame.getMethod().getName())) {
            return;
        }
        Method method = frame.getMethod();
        String className = method.getClazz().getName();
        String methodName = method.getName();
        int pc = frame.getThread().getPC();
        System.out.printf("%s.%s() #%2d %s\n", className, methodName, pc, instruction);
    }


}
