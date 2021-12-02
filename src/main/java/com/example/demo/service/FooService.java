package com.example.demo.service;

import com.example.demo.SpringContextUtils;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/22
 */

public class FooService {
    public FooService getService(){
        return SpringContextUtils.getBean(this.getClass());
    }

    public void say(){
        System.out.println("Foo say..");
    }

    public void sayByInner(){
        InnerFoo innerFoo = new InnerFoo();
        innerFoo.say();
    }

    class InnerFoo{
        public void say(){
            System.out.println("Foo say..");
            getService().say();
        }
    }
}
