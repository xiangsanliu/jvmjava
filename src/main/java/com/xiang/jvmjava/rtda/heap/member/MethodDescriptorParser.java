package com.xiang.jvmjava.rtda.heap.member;

/**
 * @author 项三六
 * @time 2019/3/31 19:14
 * @comment 解析方法描述（解析参数列表）
 */

class MethodDescriptorParser {

    private String raw;

    private int offset;

    private MethodDescriptor parsed;

    static MethodDescriptor parse(String descriptor) {
        MethodDescriptorParser parser = new MethodDescriptorParser();
        parser.raw = descriptor;
        parser.parsed = new MethodDescriptor();
        parser.startParams();
        parser.parseParamTypes();
        parser.endParams();
        parser.parseReturnType();
        parser.finish();
        return parser.parsed;
    }

    private void finish() {
        if (this.offset != this.raw.length()) {
            throwError();
        }
    }

    private void parseReturnType() {
        if (this.readChar() == 'V') {
            this.parsed.setReturnType("V");
            return;
        }
        this.unreadChar();
        String t = parseFieldType();
        if (!"".equals(t)) {
            this.parsed.setReturnType(t);
            return;
        }
        this.throwError();
    }

    private void parseParamTypes() {
        while (true) {
            String t = parseFieldType();
            if (!t.equals("")) {
                this.parsed.getParameterTypes().add(t);
            } else {
                break;
            }
        }
    }

    private String parseFieldType() {
        switch (readChar()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return parseObjectType();
            case '[':
                return parseArrayType();
            default:
                unreadChar();
                return "";
        }
    }

    private void unreadChar() {
        this.offset--;
    }

    private String parseArrayType() {
        int start = this.offset - 1;
        parseFieldType();
        int end = this.offset;
        return this.raw.substring(start, end);
    }

    private String parseObjectType() {
        String unread = this.raw.substring(this.offset);
        int index = unread.indexOf(';');
        if (index == -1) {
            throwError();
            return "";
        } else {
            int start = this.offset - 1;
            int end = this.offset + index + 1;
            this.offset = end;
            return this.raw.substring(start, end);
        }
    }

    private void startParams() {
        if (readChar() != '(') {
            throwError();
        }
    }

    private void endParams() {
        if (readChar() != ')') {
            throwError();
        }
    }

    private void throwError() {
        throw new Error("Bad descriptor: " + this.raw);
    }

    private char readChar() {
        return this.raw.charAt(this.offset++);
    }

}
