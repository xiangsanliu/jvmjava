package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/15 17:25
 * @comment
 */

@Setter
@Getter
public class ConstantDoubleInfo extends ConstantInfo {

    double val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = Double.longBitsToDouble(reader.readUint64());
    }
}
