package com.xiang.jvmjava.classfile.rtda.heap.member;

import com.xiang.jvmjava.classfile.MemberInfo;
import com.xiang.jvmjava.classfile.attribute.CodeAttribute;
import com.xiang.jvmjava.classfile.attribute.LineNumberTableAttribute;
import com.xiang.jvmjava.classfile.rtda.heap.AccessFlags;
import com.xiang.jvmjava.classfile.rtda.heap.ExceptionTable;
import com.xiang.jvmjava.classfile.rtda.heap.JvmClass;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/23 20:18
 * @comment
 */
@Getter
@Setter
public class Method extends ClassMember {

    private int maxStack;

    private int maxLocals;

    private byte[] code;

    private ExceptionTable exceptionTable;

    private LineNumberTableAttribute lineNumberTable;

    private int argSlotCount;

    public Method(JvmClass clazz, MemberInfo info) {
        this.argSlotCount = 0;
        this.clazz = clazz;
        this.copyMemberInfo(info);
        this.copyAttributes(info);
        MethodDescriptor md = MethodDescriptorParser.parse(this.descriptor);
        this.calcArgSlotCount(md.getParameterTypes());
        if (this.isNative()) {
            this.injectCodeAttribute(md.getReturnType());
        }
    }

    public static Method[] newMethods(JvmClass clazz, MemberInfo[] memberInfos) {
        Method[] methods = new Method[memberInfos.length];
        for (int i = 0; i < memberInfos.length; i++) {
            methods[i] = new Method(clazz, memberInfos[i]);
        }
        return methods;
    }

    @Override
    public void copyAttributes(MemberInfo memberInfo) {
        CodeAttribute attribute = memberInfo.getCodeAttribute();
        if (attribute != null) {
            this.maxStack = attribute.getMaxStack();
            this.maxLocals = attribute.getMaxLocals();
            this.code = attribute.getCode();
            this.lineNumberTable = attribute.getLineNumberTableAttribute();
            this.exceptionTable = new ExceptionTable(attribute.getExceptionTable(), this.getClazz().getConstantPool());
        }
    }

    public int findExceptionHandlerPC(JvmClass exClass, int pc) {
        ExceptionTable.ExceptionHandler handler = exceptionTable.findExceptionHandler(exClass, pc);
        if (handler != null) {
            return handler.getHandlerPC();
        }
        return -1;
    }

    public int getLineNumber(int pc) {
        if (this.isNative()) {
            return -2;
        }
        if (this.lineNumberTable == null) {
            return -1;
        }
        return this.lineNumberTable.getLineNumber(pc);
    }

    public boolean isBridge() {
        return 0 != (this.accessFlags & AccessFlags.ACC_BRIDGE);
    }

    public boolean isVarargs() {
        return 0 != (this.accessFlags & AccessFlags.ACC_VARARGS);
    }

    public boolean isNative() {
        return 0 != (this.accessFlags & AccessFlags.ACC_NATIVE);
    }

    public boolean isStrict() {
        return 0 != (this.accessFlags & AccessFlags.ACC_STRICT);
    }

    private void calcArgSlotCount(List<String> types) {
        types.forEach(e -> {
            this.argSlotCount++;
            if (e.equals("J") || e.equals("D")) {
                this.argSlotCount++;
            }
        });
        if (!this.isStatic()) {
            this.argSlotCount++;
        }
    }

    private void injectCodeAttribute(String type) {
        this.maxStack = 4;
        this.maxLocals = this.argSlotCount;
        switch (type.charAt(0)) {
            case 'V':
                this.code = new byte[]{-2, -79};
                break;
            case 'L':
            case '[':
                this.code = new byte[]{-2, -80};
                break;
            case 'D':
                this.code = new byte[]{-2, -81};
                break;
            case 'F':
                this.code = new byte[]{-2, -82};
                break;
            case 'J':
                this.code = new byte[]{-2, -83};
                break;
            default:
                this.code = new byte[]{-2, -84};
        }
    }


}
