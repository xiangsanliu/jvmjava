package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/15 17:49
 * @comment
 */

@Getter
@Setter
public class ConstantNameAndTypeInfo extends ConstantInfo {

    private int nameIndex;

    private int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }
}
