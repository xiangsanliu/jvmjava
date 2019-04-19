package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.attribute.CodeAttribute;
import com.xiang.jvmjava.classfile.rtda.heap.ref.ClassRef;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 项三六
 * @time 2019/4/13 10:25
 * @comment
 */

@Getter
public class ExceptionTable {

    private ExceptionHandler[] handlers;

    public ExceptionTable() {
        this.handlers = new ExceptionHandler[0];
    }

    public ExceptionTable(CodeAttribute.ExceptionTableEntry[] entries, JvmConstantPool constantPool) {
        this.handlers = new ExceptionHandler[entries.length];
        for (int i = 0; i < entries.length; i++) {
            handlers[i] = new ExceptionHandler(entries[i], constantPool);
        }
    }

    public ExceptionHandler findExceptionHandler(JvmClass exClass, int pc) {
        for (ExceptionHandler handler : handlers) {
            if (pc >= handler.startPC && pc < handler.endPC) {
                if (handler.catchType == null) {
                    return handler; // catch-all
                }
                JvmClass catchClass = handler.catchType.resolvedClass();
                if (catchClass == exClass || catchClass.isSuperClassOf(exClass)) {
                    return handler;
                }
            }
        }
        return null;
    }

    @Getter
    public static class ExceptionHandler {

        int startPC;

        int endPC;

        int handlerPC;

        ClassRef catchType;

        private ExceptionHandler(CodeAttribute.ExceptionTableEntry entry, JvmConstantPool constantPool) {
            this.startPC = entry.getStartPC();
            this.endPC = entry.getEndPC();
            this.handlerPC = entry.getHandlerPC();
            this.catchType = getCatchType(entry.getCatchType(), constantPool);
        }

        private ClassRef getCatchType(int index, JvmConstantPool constantPool) {
            if (index == 0) {
                return null;
            }
            return (ClassRef) constantPool.getConstant(index);
        }

    }


}
