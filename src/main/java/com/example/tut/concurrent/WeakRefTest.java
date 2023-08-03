package com.example.tut.concurrent;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/22
 */

public class WeakRefTest {

    public static void main(String[] args) {
        WeakReference<String> sr = new WeakReference<String>(new String("hello"));

        System.out.println(sr.get());

        // String var = sr.get();
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());
    }
}


class GCTarget {
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

class GCTargetWeakReference extends WeakReference<GCTarget> {
    // 弱引用的ID
    public String id;

    public GCTargetWeakReference(GCTarget gcTarget,
                                 ReferenceQueue<? super GCTarget> queue) {
        super(gcTarget, queue);
        this.id = gcTarget.id;
    }

    protected void finalize() {
        System.out.println("Finalizing GCTargetWeakReference " + id);
    }
}


