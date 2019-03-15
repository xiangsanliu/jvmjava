package com.xiang.jvmjava.util;

/**
 * @author 项三六
 * @time 2019/3/15 19:36
 * @comment
 */

public class ByteUtils {


    public static String bytesToHexString(byte[] src) {
        return bytesToHexString(src, src.length);
    }

    /**
     * @param src 待转换的字节数组
     * @param len 只转换字节数组中的前len个字节
     * @return 转换成的字符串, 考虑到这是对底层数据的操作,
     */
    public static String bytesToHexString(byte[] src, int len) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || len <= 0) {
            return null;
        }
        for (int i = 0; i < len; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
