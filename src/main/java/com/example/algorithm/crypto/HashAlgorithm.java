package com.example.algorithm.crypto;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author neo.zr
 * @since 2024/5/20
 */

public class HashAlgorithm {

    public static void main(String[] args) {
        String text = "abcdefh";
        System.out.println(md5(text));
        System.out.println(Arrays.toString("abcd".getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * message digest algorithm
     * input maximum length: 2^64 -1 bits / 2^61 - 1 bytes
     * output fix length: 128 bits / 16 bytes
     * 单向不可逆的加密散列函数，用于文件校验、密码存储，存在hash碰撞的情况
     */
    public static String md5(String text){
        try{
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bytes = digest.digest(text.getBytes("UTF-8"));
            System.out.println(Arrays.toString(bytes));
            return Base64.encodeBase64String(bytes);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 加盐，在hash加密的过程中，加入一个随机字符串，防止数据库被功破后使用彩虹表比对出用户密码，同时降低碰撞概率
     *
     */
    public static String md5WithSalt(String text){
        try{
            MessageDigest digest = MessageDigest.getInstance("md5");
            String salt = "salt_salt";
            text += salt;

            byte[] bytes = digest.digest(text.getBytes("UTF-8"));
            System.out.println(Arrays.toString(bytes));
            return Base64.encodeBase64String(bytes);
        }catch (Exception e){
            return null;
        }
    }


    /**
     * secure hash algorithm
     * more secure, reliable and less likely to be broken , slower compare to md5
     * output fix length: sha1 - 160 bits / 20 bytes, sha256 - 32 bytes
     */
    public static String sha(String text){

        return null;
    }

    public static String hmac(String text){
        return null;
    }
}
