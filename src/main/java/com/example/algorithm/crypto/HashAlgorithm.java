package com.example.algorithm.crypto;

import com.trip.tourop.api.TouropResponse;
import com.trip.tourop.model.SyncPriceModel;
import com.trip.tourop.util.TouropUtils;
import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author neo.zr
 * @since 2024/5/20
 */

public class HashAlgorithm {

    final static String base64PrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwtjx0rsabXQNc" +
            "ZlbOV/SN+iKwXicAZ798hBzVKWZu25ZhQ0Mjhxh3C+08+1SznGQ+Z5/q6Kuv577p" +
            "Rc6CIKzxqiyYJN87eZiIWsCp0zoPfV1TbLQoIc6khsTK2u24LxUcKit3R+wGEHN0" +
            "NNKYIOrGDvGvgG2Mt6buCJdw3azTkt11ybQPBhnju7oHIgUdX+pXkhRUbNO0quhO" +
            "z0Ykdw9kwlIh7fYI7Coc4vgaAmzL7LbbNF36TJ9CZmD79zpAhJRiHIOQv/FDQtCJ" +
            "514v4cuKMzxzbIfF6eqcu0FA4vTxxrPNr+5thQcGIWErt5snuUIpr4Z1iSegTMZ+" +
            "WojHxf19AgMBAAECggEBAJhUH30+BDp9TZ5jIcUyEMAqtahvIRO6W1jM0cX2sDQz" +
            "2kfxoXiIxlAo2dLNnNBPqiIZVcM72nQCQ5H2+PM3k52m4lYswroUMaapBJfgW5Zq" +
            "rP5zIEHQEr/E5PMxEebJv1rOBzYFq7H2kG/F8RvulDMzQqfU/5pj3eQnR9SlYovm" +
            "CYSU2qCgCxzjtn9WdAoIUL4goIdylHmJ72seS1HSlzuUmzLU+It7XABgreoBy0jf" +
            "jplAL3RLegSHj33XQNdSRZBu3sXVrZj1t0ZnHLiyVLfW8hKIlP/CTLgs5ONOQXE9" +
            "5KlRLeq2Nn1pb0/Cqq3Zg0JIHnSDAzFx4qgIa5xs+8ECgYEA11Xzm2KiygP7zaRi" +
            "OXIJiaW/zNtpJx3ID3/DtbAUrJZHjUeKLeqVojFFiUO1fu0REm5Zd7djVbULnPTO" +
            "VwIlhll4RDUYh1aJgijCdRXUWvATF0vE8gvN5RoXf2bjG6GPdAQlklsD/D8i6NiD" +
            "mdO3LGkS3hJbqUOEPB5P/+Sv++kCgYEA0hUTs1fmhFXBDMo5pjN18Lhpuftzp+Tv" +
            "HR7F3AVIU0fxAAlTaGmBl1ZrE/8YDzNt4j+sRIEfug8g1eTH3bsUudO6+MguJULe" +
            "stwCHzxCnVX3BvEHjdvc577dTUiUcOI7Omz0WpYiF/cujfY6E0nbWFapCMu4hRy7" +
            "G9zHNT3bfHUCgYAjPGlUb9t7exNlHxUDmWl+IKU1/GFEiVcHUjzQKjP32c9zitVN" +
            "lilOFQnv4Ch+0IQOpAf30wZqK+nukRCCpFNHnLRVVOrimoJ9zfWj+yJ4jvhQw5FC" +
            "mjPi3VYi/s8C4nj63wLW5BXO3PX1bBh8v2wl1DvGIvLky6uDuuIUWXOCoQKBgQC4" +
            "ZxPR2phXFMbNqTyNLKKb1+PGAnf0qblRwE9A38oqf/2FYKo7/lBoPMzk0oeV4DjV" +
            "3boir4zfzqOt3JGriamZq8Z01ZHb1ySPrxqVvFlkra6WAz/0P0dajGjIi6rwo0QF" +
            "9tlOInZuvuO4bKdxguTsV7UsKVYgUaXTwSmDDHb6eQKBgCyAugpXPfcKxVMCrD2t" +
            "jDXKxeIga3Zb6jPCO85Bh7q0/tKIMu7caBLPlioMnjEcHpNY2boFCSSnsjwIvl7P" +
            "6mzwyhEKf/JpaFBlVbYnay8Qu5bAt9grrdxMRkRSG5NQb/hSPysoBUaNlCY35fKh" +
            "/e36oql26Ms9FzHUD+nTdAeg" ;

    final static String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3Jwb3JhdGVDb2RlIjoiQ09SUC0wMDAwMDAwMDQiLCJjb3Jwb3JhdGVJRCI6NCwiY29ycG9yYXRlTG9nbyI6Imh0dHBzOi8vc2FuZGJveC5ta3Btb2JpbGUuY29tL3VwbG9hZHMvYjJiaW52bWFzdGVyL1hWbEJ6Z2IyNTAyMDUxMzM5NDYuanBlZyIsImNvcnBvcmF0ZU5hbWUiOiJUUklQLkNPTSIsImNvcnBvcmF0ZVBhcmVudENvZGUiOiJDT1JQLTAwMDAwMDAwMSIsImV4cCI6MTc0NjYwMTMxNiwiaGllcmFyY2h5IjoiVE1IQy0wMDAwMDAwMDEtMDAwMDAwMDA0IiwiaWF0IjoxNzQ2NTk3NzE2LCJtZXJjaGFudEtleSI6IkNPUlAtMDAwMDAwMDA0I1RNSEMtMDAwMDAwMDAxLTAwMDAwMDAwNCIsIm91dGxldENvZGUiOiIiLCJyb2xlTmFtZSI6IlNFR01FTlRBVElPTiBUUklQIiwic2VnbWVudENvZGUiOiJUTVNDLTAwMDAwMDAwOSIsInVzZXJFbWFpbCI6IkppbW15LmFtYW5AdHJpcC5jb20iLCJ1c2VyRmlyc3ROYW1lIjoiSmltbXkiLCJ1c2VySUQiOjE1LCJ1c2VyTGFzdE5hbWUiOiJUUklQIiwidXNlclBob25lIjoiKzYyODEyMzQ1Njc4OSJ9.X1vBd36SPgMlyzZ4Q0_w-VHdCS4TcO6dtuKs_yxXMpQ";

    private static String str2Hex(String str){
        StringBuilder hexString = new StringBuilder();
        for (char ch : str.toCharArray()) {
            // Convert each character to its hexadecimal representation
            String hex = Integer.toHexString((int) ch);
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String byteToArray(byte[] data) {
        StringBuffer resultBuffer = new StringBuffer("");
        for (int i = 0; i < data.length; i++) {
            String substring = Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3);
            resultBuffer.append(substring);
        }
        return resultBuffer.toString();
    }


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

    public static void main(String[] args) throws Exception {
//        System.out.println(Instant.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")));
        String timestamp = "2025-05-06T19:00:00Z";
        signBk("{\"partnerRefNo\":\"TRIPTEST1081\",\"bookerFirstName\":\"John\",\"bookerLastName\":\"Lee\",\"bookerEmail\":\"john.doe@example.com\",\"bookerNationality\":\"USA\",\"productCode\":\"200070\",\"bookingDate\":\"2025-05-13\",\"bookingList\":[{\"productOptionCode\":\"RTKLK001\",\"visitorFirstName\":\"Alex\",\"visitorIdType\":\"PASSPORT\",\"visitorIdNumber\":\"1234567890098765\",\"visitorNationality\":\"UK\",\"visitorEmail\":\"alex.smith@example.com\"},{\"productOptionCode\":\"RTKLK001\",\"visitorFirstName\":\"Alex\",\"visitorIdType\":\"PASSPORT\",\"visitorIdNumber\":\"1234567890098765\",\"visitorNationality\":\"UK\",\"visitorEmail\":\"alex.smith@example.com\"},{\"productOptionCode\":\"RTKLK002\",\"visitorFirstName\":\"Alex\",\"visitorIdType\":\"PASSPORT\",\"visitorIdNumber\":\"1234567890098765\",\"visitorNationality\":\"UK\",\"visitorEmail\":\"alex.smith@example.com\"}]}", timestamp);

        signPay("{\"docNo\":\"TMTRX-20250507-yjnD7n\",\"accountNumber\":\"0010733607\"}", timestamp);

       System.out.println(Math.random() * 0xFFFF);
       System.out.println(0xFFFF);
    }

    private static void signPay(String body, String timestamp) throws Exception {
        body = body.replaceAll("\\s+", "");
        System.out.println(body);
        StringBuilder dataBuilder = new StringBuilder("POST:");
        dataBuilder.append("/v1.0/private/partner/transaction/booking/payment");
        dataBuilder.append(":");
        dataBuilder.append(accessToken);
        dataBuilder.append(":");
        dataBuilder.append(timestamp);
        dataBuilder.append(":");
        dataBuilder.append(str2Hex(body));
        System.out.println(encryptByPrivateKey(base64PrivateKey, dataBuilder.toString()));
    }

    private static void signBk(String body, String timestamp) throws Exception {
        body = body.replaceAll("\\s+", "");
        System.out.println(body);
        StringBuilder dataBuilder = new StringBuilder("POST:");
        dataBuilder.append("/v1.0/private/partner/tourism/booking/inquiry");
        dataBuilder.append(":");
        dataBuilder.append(accessToken);
        dataBuilder.append(":");
        dataBuilder.append(timestamp);
        dataBuilder.append(":");
        dataBuilder.append(str2Hex(body));
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


    public static String hmac(String text){
        return null;
    }
}
