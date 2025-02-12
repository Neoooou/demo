package com.example.app.controller;

import com.example.app.service.IHelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/21
 */

@RestController("/helloWorld")
public class HelloWorldController {

    @Autowired
    private IHelloWorld iHelloWorld;

    @RequestMapping("/index")
    public @ResponseBody String hello(HttpRequest request){
        return iHelloWorld.sayHello();
    }
}
