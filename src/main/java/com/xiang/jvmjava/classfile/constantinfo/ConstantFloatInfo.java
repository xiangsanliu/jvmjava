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

@Setter
@Getter
public class ConstantFloatInfo extends ConstantInfo {

    float val;

    @Override
    public void readInfo(ClassReader reader) {
        int bytes = reader.readUint32();
        this.val = Float.intBitsToFloat(bytes);
    }

}
