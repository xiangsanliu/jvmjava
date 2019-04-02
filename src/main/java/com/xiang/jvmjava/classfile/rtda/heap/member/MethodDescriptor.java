package com.xiang.jvmjava.classfile.rtda.heap.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 项三六
 * @time 2019/3/31 18:46
 * @comment
 */

@Setter
@Getter
@ToString
class MethodDescriptor {

    private List<String> parameterTypes;

    private String returnType;

    MethodDescriptor() {
        this.parameterTypes = new ArrayList<>();
    }
}
