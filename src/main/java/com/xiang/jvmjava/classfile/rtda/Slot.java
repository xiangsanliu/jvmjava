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

    private int num32;

    private long num64;

    private JvmObject ref;

}
