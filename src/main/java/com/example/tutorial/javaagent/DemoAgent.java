package com.example.tutorial.javaagent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Random;

/**
 * @Author: Neo.zr
 * @Since: 2026/3/11
 **/
public class DemoAgent {


    static class MyClassFileTransformer implements ClassFileTransformer {
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("transform class: " + className);
            return classfileBuffer;
        }
    }

}
