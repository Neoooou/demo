package com.example.algorithm.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

/**
 * @author neo.zr
 * @since 2024/5/20
 */

public class HashAlgorithm {

    public static void main(String[] args) throws Exception {
        String body = "{\"stringValue\":\"TEST\",\"intValue\":1,\"boolValue\":true}";
        body = body.replaceAll("\\s+", "");
        StringBuilder dataBuilder = new StringBuilder("POST:");
        dataBuilder.append("/v1.0/private/partner/ping");
        dataBuilder.append(":");
        dataBuilder.append("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3Jwb3JhdGVDb2RlIjoiQ09SUC0wMDAwMDAwMDQiLCJjb3Jwb3JhdGVJRCI6NCwiY29ycG9yYXRlTmFtZSI6IlRSSVAuQ09NIiwiY29ycG9yYXRlUGFyZW50Q29kZSI6IiIsImV4cCI6MTc0MTc2MDYwNSwiaGllcmFyY2h5IjoiVE1IQy0wMDAwMDAwMDQiLCJpYXQiOjE3NDE3NTcwMDUsIm1lcmNoYW50S2V5IjoiQ09SUC0wMDAwMDAwMDQjVE1IQy0wMDAwMDAwMDQiLCJvdXRsZXRDb2RlIjoiIiwicm9sZU5hbWUiOiJDb3Jwb3JhdGVBZG1pbiIsInNlZ21lbnRDb2RlIjoiVE1TQy0wMDAyIiwidXNlckVtYWlsIjoiSmltbXkuYW1hbkB0cmlwLmNvbSIsInVzZXJGaXJzdE5hbWUiOiJKb2huIiwidXNlcklEIjo0LCJ1c2VyTGFzdE5hbWUiOiJEb2UiLCJ1c2VyUGhvbmUiOiIwOTgyMjc2NjM3NzIifQ.1IN4QxaSAld5lrlKUaDzfjPWDL_7M0F0etTJFZV5Aug");
        dataBuilder.append(":");
        dataBuilder.append("2025-03-11T11:00:00Z");
        dataBuilder.append(":");
        dataBuilder.append(str2Hex(body));

        String base64PrivateKey =
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDJNRD9AmZUGq1i" +
                "JHpIUF2V77JzB1OVB7Hda4HOyUFh8Jlhe6nfgy7WqNNWOIxtt9zPpJ/FVj8U+W9j" +
                "Js51Jr+WiSzb1nvhxCzrlvShLGWqYuEVd+djPm9/eGuiNUZ2A49jjpXFOZcsBOC6" +
                "NEAaWmYhh6I/iytaaZIZNPV4LTGiBcGdDYz/CmKO/orUbykLqiwe1d7wMWCFqkrW" +
                "ld1V+wATfgzAgCSUCg+zoz7XvOlH/yEv7/z02Z8AHI8uVhA6b33jWqKfdZ+glohx" +
                "HnQ9SG7e0FVhtESaeRtGunLIHYlrW90gYkOJLZafkO6BWRHxlfAAjF/suQMYpTB9" +
                "VMbGsKQhAgMBAAECggEAXcb26QVCTja00sYvnB6gz64NHOGwSghxFMLzmD5i09QA" +
                "mNe7P+pzXScIgOoQQV7tcnXG5YGiGxN6n9rJZP6vUukSWE/qIzp8uTz9PVVk+Pnm" +
                "8EuXM8E41E960vpDoeNc5Ejjcy25UJHhb8fEjMPPUBdYPoeNqDEGH+t38RAXTuzN" +
                "Z28P5BiE4esf9Iis3H+cjqQ+QmlpTikTqkj34iYT0sul+ivWL/8n7MymAs4iIjFI" +
                "ZlL4mJbezY5BuseD3LVl/Rq85R2c3xM9ZSVRAAI/vyzRax2klfqB22tUEZek7pTH" +
                "JilEIetXIXBM8xmgQPQsxqnlOQsUpkJbr0Ta7Dj5MQKBgQD5S72fiIGLKgyS6n1z" +
                "7CUdkpnBsDU6qTjzIfy7edjoz2h9Po0wZGqsPHexAh14NxvRIw6E9Rm4juMfO0oI" +
                "upUEPO9bihC/+uIHPE2Sh3q6PWxqrXkHEIwO7G1zgSdRWVfkB/aL3JD3Pb8GfunS" +
                "enfnK+xosMZJI+SOmD41cW3JvQKBgQDOnkNrmM9I1j+4gWbWj+oXGk3bmpZP0gNz" +
                "jYDmsmdz8emFGY5PlUPzntMX5ojdEfcyLval/VtqiGoP2gcFOe2LcFriYLXkwynk" +
                "/x7sz/eYpHhE/Ey+GWrohA/nRkKbr5t64qCmX8lxtQPptR9oJGagEYZHWXJucrYs" +
                "XJ1TT/tgNQKBgBK3FO09GjyQU5FT+UcSm1a1AsX/rH9S5OubyZVpeFdB+t4sK/1O" +
                "DJQRlgq71eqIeJYiw5xHqj6ou3RExzIQj89Zo76Dhu5ir0VLyacOdLA2nEUF8OgO" +
                "3fTg2vao44K/6lE8J84oKNnm0Mh0Dqm2d/nq2jyhyMEE6Mursj/g3BUhAoGAb+YM" +
                "c2hF0ped2OnT7R3x1GM9iVJWV5JQpjMbfVVfa862/ouRpA4dyAHbJxWGR9vj+Xyv" +
                "vcPRz4djkYQtyynNOXg28GUZ/XOo68kzUWNsH3HrZMkTMt0HJjfneQR3LGPVm57D" +
                "DHfR3FABd3/NK5STM9tp0phhvelIOgxKdAVcCYUCgYAd9S41ZoVuBWMHmCpBbVQa" +
                "vkWZwpaXRbe9/0oiLG7aHstcZglY2MEyyy/F7WmlkrOpHoG+g0Ovs6m+3CwXqVjH" +
                "l9bwr4yNImRw41a1BOAmJBZ8S1ifNlJ6jTKoRavS0dF8H/Mus224sS90tO7LhW7t" +
                "SchtLj9FGR9YOapgHWlttA==" ;

        System.out.println(encryptByPrivateKey(base64PrivateKey, dataBuilder.toString()));
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

//    public static String rsa() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//        // 生成RSA密钥对
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(2048); // 密钥长度为2048位
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        var privateKey = keyPair.getPrivate();
//
//        // 要加密的原始数据
//        String originalData = "Hello, RSA!";
//        System.out.println("Original Data: " + originalData);
//
//        // 使用公钥进行加密
//        Cipher encryptCipher = Cipher.getInstance("RSA");
//        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] encryptedData = encryptCipher.doFinal(originalData.getBytes());
//        System.out.println("Encrypted Data: " + bytesToHex(encryptedData));
//
//        // 使用私钥进行解密
//        Cipher decryptCipher = Cipher.getInstance("RSA");
//        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decryptedData = decryptCipher.doFinal(encryptedData);
//        System.out.println("Decrypted Data: " + new String(decryptedData));
//    }



    // 辅助方法：从Base64编码的字符串中获取PublicKey对象
    private static String encryptByPrivateKey(String base64PrivateKey, String content) throws Exception {
        // private Key
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64PrivateKey));


        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(privateKey);
        signature.update(content.getBytes("UTF-8"));
        byte[] bytes = signature.sign();
        return Base64.encodeBase64String(bytes);
    }

    public static String SHA(String str) {
        if (str == null) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.reset();
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            String hex = Integer.toHexString(0xFF & byteArray[i]);
            if (hex.length() == 1)
                md5StrBuff.append("0").append(hex);
            else
                md5StrBuff.append(hex);
        }
        return md5StrBuff.toString();
    }

    private static String str2Hex(String str){
        StringBuilder hexString = new StringBuilder();
        for (char ch : str.toCharArray()) {
            // Convert each character to its hexadecimal representation
            String hex = Integer.toHexString((int) ch);
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public static String hmac(String text){
        return null;
    }
}
