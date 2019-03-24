package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantLiteralInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/15 17:23
 * @comment
 */

@Getter
@Setter
public class ConstantIntegerInfo extends ConstantLiteralInfo {

    private int val;

    @Override
    public void readInfo(ClassReader reader) {
        // TODO convert to int32
        this.val = reader.readUint32();
    }

    @Override
    public Object value() {
        return val;
    }
}
