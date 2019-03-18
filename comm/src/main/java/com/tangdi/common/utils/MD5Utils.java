package com.tangdi.common.utils;

import java.security.MessageDigest;

public class MD5Utils {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * MD5加密
     *
     */
    public static String MD5Encrypt(String str) {
        MessageDigest md;
        String result = "";
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] bt = str.getBytes("UTF-8");
            md.update(bt);
            result = bytesToHexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toLowerCase();
    }


    public static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
