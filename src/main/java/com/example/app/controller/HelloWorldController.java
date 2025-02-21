package com.example.app.controller;

import com.example.app.service.IHelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/redirect")
    public String redirect(HttpRequest request){
        return "redirect:https://ctrip.com";
    }

    @RequestMapping("/redirect1")
    public ModelAndView redirect1(HttpRequest request){
        ModelAndView model = new ModelAndView();

        // 永久性重定向：301
        model.setStatus(HttpStatus.MOVED_PERMANENTLY);

        // 临时性重定向：302
        model.setStatus(HttpStatus.FOUND);

        model.setView(null);
        return model;
    }
}
