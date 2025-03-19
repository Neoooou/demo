package com.example.app.service.impl;

import com.example.app.annotation.ServiceLog;
import com.example.app.service.IHello;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/9
 */
@Service
public class HelloImpl implements IHello {

    @Value("${greetings}")
    public String greetings;

    @ServiceLog
    @Override
    public String sayHello() {
        return greetings;
    }
}
