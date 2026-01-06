//package com.example.algorithm.crypto;
//
//import com.lazada.lazop.api.LazopClient;
//import com.lazada.lazop.api.LazopRequest;
//import com.lazada.lazop.api.LazopResponse;
//import com.lazada.lazop.util.ApiException;
//import com.lazada.lazop.util.Constants;
//
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.Arrays;
//import java.util.Map;
//
//import static com.lazada.lazop.util.LazopUtils.areNotEmpty;
//
//public class LazopTest {
//
//    static LazopClient client;
//
//    static {
//        String url = "https://api.lazada.com.my/rest";
//        String appKey = "106398";
//        String appSecret = "mznwlT5cXVPv7EVmVxf3yseHR87Q6P2D";
//        client = new LazopClient(url, appKey, appSecret);
//    }
//
//    public static void main(String[] args) throws ApiException {
//        LazopRequest request = new LazopRequest();
//        request.setApiName("/seller/get");
//        request.setHttpMethod("GET");
//        request.addApiParameter("access_token", "50000000e21k2tynrZDzg2cjqeWwdZhdu12e326014MvydjFNKwgsTkHwmaJF0x");
//        LazopResponse response = client.execute(request);
//        System.out.println(response.getBody());
//
//    }
//
//
//    public static void auth() throws ApiException {
//        LazopRequest request = new LazopRequest();
//        request.setApiName("/auth/token/createWithOpenId");
//        request.addApiParameter("code", "0_106398_QDFW8EDhxcdh1Y3I9OzHl6X5231");
//        LazopResponse response = client.execute(request);
//        System.out.println(response.getBody());
//    }
//
//    /**
//     * Sign the API request with body.
//     */
//    public static String signApiRequest(Map<String, String> params, String body, String appSecret, String signMethod, String apiName) throws IOException {
//        // first: sort all text parameters
//        String[] keys = params.keySet().toArray(new String[0]);
//        Arrays.sort(keys);
//
//        // second: connect all text parameters with key and value
//        StringBuilder query = new StringBuilder();
//        query.append(apiName);
//        for (String key : keys) {
//            String value = params.get(key);
//            if (areNotEmpty(key, value)) {
//                query.append(key).append(value);
//            }
//        }
//
//        // third：put the body to the end
//        if (body != null) {
//            query.append(body);
//        }
//
//        // next : sign the whole request
//        byte[] bytes = null;
////
////        if (signMethod.equals(Constants.SIGN_METHOD_HMAC)) {
////            bytes = encryptWithHmac(query.toString(), appSecret);
////        } else if (signMethod.equals(Constants.SIGN_METHOD_SHA256)) {
////            bytes = encryptHMACSHA256(query.toString(), appSecret);
////        }
//
//        // finally : transfer sign result from binary to upper hex string
//        return byte2hex(bytes);
//    }
//
//
//    private static byte[] encryptHMACSHA256(String data, String secret) throws IOException {
//        byte[] bytes = null;
//        try {
//            SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), Constants.SIGN_METHOD_HMAC_SHA256);
//            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//            mac.init(secretKey);
//            bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
//        } catch (GeneralSecurityException gse) {
//            throw new IOException(gse.toString());
//        }
//        return bytes;
//    }
//
//    /**
//     * Transfer binary array to HEX string.
//     */
//    public static String byte2hex(byte[] bytes) {
//        StringBuilder sign = new StringBuilder();
//        for (int i = 0; i < bytes.length; i++) {
//            String hex = Integer.toHexString(bytes[i] & 0xFF);
//            if (hex.length() == 1) {
//                sign.append("0");
//            }
//            sign.append(hex.toUpperCase());
//        }
//        return sign.toString();
//    }
//}
