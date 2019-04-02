package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantInfo;
import lombok.Getter;

/**
 * @author 项三六
 * @time 2019/4/2 10:23
 * @comment
 */

@Getter
public class ConstantMethodHandleInfo extends ConstantInfo {

    private int referenceKind;

    private int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }

}
