//package com.example.tutorial;
//
//import com.caucho.hessian.io.Hessian2Input;
//import com.caucho.hessian.io.Hessian2Output;
//import com.caucho.hessian.io.SerializerFactory;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * @author neo.zr
// * @since 2024/1/15
// */
//
//public class HessianSerializer {
//
//    SerializerFactory serializerFactory = new SerializerFactory();
//
//    public static void main(String[] args) {
//
//    }
//
//    /**
//     * 反序列化
//     *
//     * @param bytes
//     * @return
//     */
//    public Object deserializeObject(byte[] bytes) {
//        InputStream is = new ByteArrayInputStream(bytes);
//        Hessian2Input h2in = new Hessian2Input(is);
//        h2in.setSerializerFactory(serializerFactory);
//        try {
//            return h2in.readObject();
//        } catch (Exception e) {
//            // 简单异常处理，把CheckedException转换为RuntimeException
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                h2in.close();
//            } catch (IOException e) {
//                // ignore
//            }
//        }
//    }
//
//    /**
//     * 序列化
//     * @param obj
//     * @return
//     */
//    public byte[] serializeObject(Object obj) {
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        Hessian2Output h2out = new Hessian2Output(os);
//        h2out.setSerializerFactory(serializerFactory);
//        try {
//            h2out.writeObject(obj);
//            h2out.flush();
//        } catch (Exception e) {
//            // 简单异常处理，把CheckedException转换为RuntimeException
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                h2out.close();
//            } catch (IOException e) {
//                // ignore
//            }
//        }
//        return os.toByteArray();
//    }
//}
