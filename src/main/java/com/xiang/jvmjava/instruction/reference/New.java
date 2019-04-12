package com.xiang.jvmjava.instruction.reference;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import com.xiang.jvmjava.classfile.rtda.heap.JvmConstantPool;
import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import com.xiang.jvmjava.classfile.rtda.heap.ref.ClassRef;
import com.xiang.jvmjava.instruction.base.Index16Instruction;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/28 14:17
 * @comment 通过索引从当前类的运行时常量池中找到一个类符号引用。
 * 解析这个类符号引用，拿到类数据，然后创建对象，并把对象引用推入栈顶
 */

public class New extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        JvmConstantPool constantPool = frame.getMethod().getClazz().getConstantPool();
        ClassRef classRef = (ClassRef) constantPool.getConstant(this.index);
        JvmClass clazz = classRef.resolvedClass();
        if (!clazz.isInitStarted()) {
            frame.revertNextPC();
            initClass(frame.getThread(), clazz);
            return;
        }
        if (clazz.isInterface() || clazz.isAbstract()) {
            throw new InstantiationError();
        }
        JvmObject ref = clazz.newObject();
        frame.getOperandStack().pushRef(ref);
    }
}
