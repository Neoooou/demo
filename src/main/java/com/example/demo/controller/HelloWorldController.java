package com.example.demo.controller;

import com.example.demo.service.IHelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/21
 */

@RestController("/helloWorld")
public class HelloWorldController {

    @Autowired
    private IHelloWorld iHelloWorld;

    @RequestMapping("/index")
    public @ResponseBody String hello(HttpServletRequest request){
        return iHelloWorld.sayHello();
    }
}
