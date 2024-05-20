package com.example.tutorial.cropt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author neo.zr
 * @since 2024/5/20
 *  MD5(Message Digest Algorithm)
 *
 *
 *
 */

public class SignatureUtils {
    final static String alog = "HmacSHA256";

    /**
     *a6pSztU5etJqsDWidxigdvu3hVtmtxhYhnJU3n7nN2Y%3D
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String stringToSign = "123";
        String secret = "abc";
        Mac hmac = Mac.getInstance(alog);
        hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = hmac.doFinal(stringToSign.getBytes("UTF-8"));
        System.out.println(new String(Base64.encodeBase64(signData)));


        md5foo();
    }

    public static void md5foo() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String str = "abcd";
        byte[] theMD5digest = md.digest(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(Base64.encodeBase64(theMD5digest)));
    }
}
