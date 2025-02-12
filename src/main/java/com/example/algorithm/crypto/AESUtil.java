//package com.example.algorithm.crypto;
//
//import com.alibaba.druid.util.StringUtils;
//import org.apache.commons.codec.binary.Base64;
//
//import javax.crypto.*;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//public class AESUtil {
//
//    private static final String defaultCharset = "UTF-8";
//    private static final String KEY_AES = "AES";
//    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
//
//    public static void main(String[] args) throws Exception {
//        String data = "ajnblabffopapiehnneihinblopeaobejkofddmpganakhaikmjfbbommcbajppnplaodncgbbonhcfaokdiepdgpdfjbhgnekjncnojfdpnbmlbcilghjncfcfhbndfaibjkknggpeeoddkhoijgajcpkjjcphikldkheadbgihnnnckdcgakmomgkccdldlceclfepadpmbpeadddgiaalmjgejbbbfpmnapfnmafdhmibnkomoecjkplbkfgflodamfmpmlamflmbonlhkpfbkeembfeegjdpjpdjdkphcbljjnacpinjiikkgncmbofgneijndljmcfjjmlcmhjmkbmliccnnphieknlefbphmpmgmdkijccdalfnigfcjoccfiphkfljkgomebmekbegaeeojefdlhcnplgjiacfhehineebppjmamfhfgdpikdekpfpfigcblcellojfgmjdbalaffklaokfgpejcokiipoolkiapeeabjnfejbfpnamfonbllakgpickehdikbkeefoohcibhlcinmiemnehofpcgjpnnkiffbghlhpffjkkfnmnokpndcafpijkjgikfjdeelmbhdmekeleagbkiebageepebebjokkacbmfllapgombhagaigkbmohejpcomdnb";
//        String decData = decrypt(data, "D2DN340LGYSsBrP6", "5693259981590433");
//        System.out.println(decData);
//
//        System.out.println(encrypt(decData, "D2DN340LGYSsBrP6", "5693259981590433"));
//    }
//    /**
//     * 加密方法
//     *
//     * @param encData   要加密的数据
//     * @param secretKey 密钥 ,16位的数字和字母
//     * @param vector    初始化向量,16位的数字和字母
//     */
//    public static String encrypt(String encData, String secretKey, String vector) throws Exception{
//        byte[] raw = secretKey.getBytes(StandardCharsets.UTF_8);
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
//        IvParameterSpec iv = new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8));// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//        byte[] encrypted = cipher.doFinal(encData.getBytes(StandardCharsets.UTF_8));
//        return encodeBytes(encrypted);
//    }
//
//    /**
//     * 解密方法
//     *
//     * @param data   要解密的数据
//     * @param secretKey 密钥 ,16位的数字和字母
//     * @param vector    初始化向量,16位的数字和字母
//     */
//    public static String decrypt(String data, String secretKey, String vector) throws Exception {
//        byte[] raw = secretKey.getBytes(StandardCharsets.UTF_8);
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        IvParameterSpec iv = new IvParameterSpec(vector.getBytes(StandardCharsets.UTF_8));
//        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//        byte[] bytes = decodeBytes(data);
//        byte[] original = cipher.doFinal(bytes);
//        return new String(original, StandardCharsets.UTF_8);
//    }
//
//
//    /**
//     * 转16进制
//     *
//     * @param bytes
//     * @return
//     */
//    public static String encodeBytes(byte[] bytes) {
//        StringBuffer strBuf = new StringBuffer();
//        for (int i = 0; i < bytes.length; i++) {
//            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
//            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
//        }
//        return strBuf.toString();
//    }
//
//    /**
//     * 转字节数组
//     *
//     * @param str
//     * @return
//     */
//    public static byte[] decodeBytes(String str) {
//        byte[] bytes = new byte[str.length() / 2];
//        for (int i = 0; i < str.length(); i += 2) {
//            char c = str.charAt(i);
//            bytes[i / 2] = (byte) ((c - 'a') << 4);
//            c = str.charAt(i + 1);
//            bytes[i / 2] += (c - 'a');
//        }
//        return bytes;
//    }
//
//
//    public static String aesEncrypt(String data, String key, String iv) {
//        try {
//            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
//            int blockSize = cipher.getBlockSize();
//            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
//            int plaintextLength = dataBytes.length;
//            if (plaintextLength % blockSize != 0) {
//                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
//            }
//            byte[] plaintext = new byte[plaintextLength];
//            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
//
//            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
//            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
//
//            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
//            byte[] encrypted = cipher.doFinal(plaintext);
//            return new Base64().encodeAsString(encrypted);
//
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
//
//
//    /**
//     * 加解密
//     *
//     * @param data
//     * @param key
//     * @param mode
//     * @return
//     */
//    private static String doAES(String data, String key, int mode) {
//        try {
//            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
//            byte[] content;
//            if (encrypt) {
//                content = data.getBytes(defaultCharset);
//            } else {
//                content = Base64.decodeBase64(data.getBytes());
//            }
//            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(defaultCharset), KEY_AES);
//            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
//            cipher.init(mode, keySpec);// 初始化
//            byte[] result = cipher.doFinal(content);
//            if (encrypt) {
//                return new String(Base64.encodeBase64(result));
//            } else {
//                return new String(result, defaultCharset);
//            }
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
//
//    /**
//     * 使用 AES 加密
//     *
//     * @param key       加密密钥
//     * @param plainText 明文数据
//     * @return
//     */
//    public static byte[] encryptUsingAES(byte[] key, String plainText) {
//        byte[] content = new byte[0];
//        try {
//            content = plainText.getBytes("utf8");
//            SecretKey secretKey = new SecretKeySpec(key, "AES");
//            //6.根据指定算法AES自成密码器
//            Cipher cipher = Cipher.getInstance("AES/ECB/Pkcs5Padding");
//            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//            //8.解密
//            return cipher.doFinal(content);
//        } catch (Exception e) {
//
//        }
//        return content;
//    }
//
//
//}
