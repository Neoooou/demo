package com.example.demo.service.impl;

import com.example.demo.annotations.OutPower;
import com.example.demo.service.IHelloWorld;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/9
 */
@Service
public class HelloWorldImpl implements IHelloWorld, InitializingBean {

    @Value("${field}")
    public static String field;

    @OutPower
    @Override
    public String sayHello() {
        return "hello world";
    }

    public String getField(){
        return this.field;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[HelloWorldImpl],  " + getField());
    }
}
