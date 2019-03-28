package com.xiang.jvmjava.classfile.rtda;

import com.xiang.jvmjava.classfile.rtda.heap.JvmObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 项三六
 * @time 2019/3/16 18:56
 * @comment
 */

@Setter
@Getter
@ToString
public class Slot {

    public int num32;

    public long num64;

    public JvmObject ref;

}
