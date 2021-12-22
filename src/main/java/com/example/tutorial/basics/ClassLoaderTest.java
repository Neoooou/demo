package com.example.tutorial.basics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 类加载三种方式：
 * 1。 命令行启动由JVM初始化加载
 * 2。Class.forName（）方法动态加载
 * 3。ClassLoader.loadClass()方法动态加载
 *
 * 类加载器：
 * AppClassLoader:应用类加载器，负责加载核心类，自己写的类
 * ExtClassLoader：扩展类加载器，负责加载扩展类库
 * BootStrap：JVM内置的加载器
 * @Author: neo.zr
 * @Created on: 2021/12/21
 */

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        /**
         * 只把.class文件加载到jvm中
         */
        Class<?> c = loader.loadClass(Entity.class.getName());

        Constructor[] constructors = c.getConstructors();

        Constructor[] declaredConstructors = c.getDeclaredConstructors();

        /**
         * 将类的.class文件加载到jvm中，对类进行解释，执行类中的static块
         */
        Class<?> c1 = Class.forName(Entity.class.getName());

        Entity entity = (Entity) c.getConstructor(Integer.class, String.class, Double.class).newInstance(1, "jack", 22.2D);

    }
}
