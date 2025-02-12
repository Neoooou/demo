package com.example.tutorial.concurrent;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/22
 */

public class WeakRefTest {

    public static void main(String[] args) {
        GCTargetWeakReference reference = new GCTargetWeakReference(new GCTarget("MOCK_ID"));

        System.gc();                //通知JVM的gc进行垃圾回收

        System.out.println(reference.get());
    }


    /**
     * weak reference demo.
     */
    static class GCTargetWeakReference extends WeakReference<GCTarget> {
        // 弱引用的ID
        public String id;

        public GCTargetWeakReference(GCTarget gcTarget) {
            super(gcTarget);
            this.id = gcTarget.id;
        }

        protected void finalize() {
            System.out.println("Finalizing GCTargetWeakReference " + id);
        }
    }

    static class GCTarget {
        // 对象的ID
        public String id;

        // 占用内存空间
        byte[] buffer = new byte[1024];

        public GCTarget(String id) {
            this.id = id;
        }

        protected void finalize() throws Throwable {
            // 执行垃圾回收时打印显示对象ID
            System.out.println("Finalizing GCTarget, id is : " + id);
        }
    }

}






