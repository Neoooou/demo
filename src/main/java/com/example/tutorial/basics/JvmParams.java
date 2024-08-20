package com.example.tutorial.basics;

/**
 * @Author: neo.zr
 * @Created on: 2021/12/21
 */

public class JvmParams {
    public static void printJVMOpts() {
        /**
         * 堆：
         *   - 年轻代（young generation）：eden、s0、s1
         *   - 老年代（old generation）:
         *   - 元空间(meta space)：jdk1.8之前为perm generation - 持久代，占用jvm内存，jdk1.8之后使用物理内存
         */


        // 堆初始化大小 xms， default：系统内存 / 64
        Long heapInitMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;

        // 堆最大大小, xmx， default：系统内存 / 4
        Long heapMaxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-xms: " + heapInitMemory + "mb");
        System.out.println("-xmx: " + heapMaxMemory + "mb");
    }

}
