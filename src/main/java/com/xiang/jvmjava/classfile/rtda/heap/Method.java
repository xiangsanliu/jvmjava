package com.xiang.jvmjava.classfile.rtda.heap;

import com.xiang.jvmjava.classfile.MemberInfo;
import com.xiang.jvmjava.classfile.attribute.CodeAttribute;
import lombok.Getter;
import lombok.Setter;

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

    private int argSlotCount;

    public Method() {
        this.argSlotCount = 0;
    }

    static Method[] newMethods(JvmClass clazz, MemberInfo[] memberInfos) {
        Method[] methods = new Method[memberInfos.length];
        for (int i = 0; i < memberInfos.length; i++) {
            methods[i] = new Method();
            methods[i].clazz = clazz;
            methods[i].copyMemberInfo(memberInfos[i]);
            methods[i].copyAttributes(memberInfos[i]);
            methods[i].calcArgSlotCount();
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
        }
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

    private void calcArgSlotCount() {
        MethodDescriptor parsedDescriptor = MethodDescriptorParser.parse(this.descriptor);
        parsedDescriptor.getParameterTypes().forEach(e -> {
            this.argSlotCount++;
            if (e.equals("J") || e.equals("D")) {
                this.argSlotCount++;
            }
        });
        if (!this.isStatic()) {
            this.argSlotCount++;
        }
    }


}
