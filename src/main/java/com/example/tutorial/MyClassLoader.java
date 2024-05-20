package com.example.tutorial;

import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author neo.zr
 * @since 2024/1/26
 */

public class MyClassLoader {

    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ClassLoader appClassloader = Thread.currentThread().getContextClassLoader();
//        Class apiInterface = null;
//        ServiceLoader.load(apiInterface, appClassloader);
        MyClassLoader o = new MyClassLoader();
        new Thread(()->System.out.println(o.test())).start();
        new Thread(()->System.out.println(o.test())).start();
        new Thread(()->System.out.println(o.test())).start();
        new Thread(()->System.out.println(o.test())).start();

    }

    static class DemoClassLoader extends ClassLoader {

    }

    public int test(){
        Integer val = map.get("a");
        if(val != null){
            return val;
        }
        synchronized (this){
            map.put("a", 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return map.get("a");
    }
}
