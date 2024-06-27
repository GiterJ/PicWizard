package com.hit.project.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtil {
    public static String encryptPassword(String password) {
        try {
            // 创建MessageDigest实例，指定SHA-256算法
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 对密码进行散列
            md.update(password.getBytes());
            // 获取散列后的字节数组
            byte[] digest = md.digest();
            // 将字节数组转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            // 返回加密后的密码
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

}
