package com.xiang.jvmjava.classfile.constantinfo;

import com.xiang.jvmjava.classfile.ClassReader;
import com.xiang.jvmjava.classfile.ConstantInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 项三六
 * @time 2019/3/15 17:48
 * @comment
 */

@Getter
@Setter
public class ConstantUtf8Info extends ConstantInfo {

    private String str;

    @Override
    public void readInfo(ClassReader reader) {
        int len = reader.readUint16();
        byte[] bytes = reader.readBytes(len);
        this.str = new String(bytes);
    }
}
