package com.example.app.service;

import com.example.app.util.SpringContextUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/22
 */

public class FooService {
    @Transactional
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
