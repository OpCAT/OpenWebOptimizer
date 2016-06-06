package com.opcat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MD5Util {
    public static String getMd5Str(String plainText) {
        return getMd5Str(plainText.getBytes());
    }

    public static String getMd5Str(byte[] bytes) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        byte[] cipherData = messageDigest.digest(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte cipher : cipherData) {
            String hexStr = Integer.toHexString(cipher & 0xff);
            sb.append(hexStr.length() == 1 ? "0" + hexStr : hexStr);
        }
        return sb.toString();
    }
}
