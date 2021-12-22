package com.example.app.service.impl;

import com.example.app.annotations.OutPower;
import com.example.app.service.IHelloWorld;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/9
 */
@Service
public class HelloWorldImpl implements IHelloWorld {

    @Value("${greetings}")
    public static String greetings;

    @OutPower
    @Override
    public String sayHello() {
        return greetings;
    }
}
