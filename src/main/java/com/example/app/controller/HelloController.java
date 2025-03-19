package com.example.app.controller;

import com.example.app.service.IHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/21
 */

@RestController("")
public class HelloController {

    @Autowired
    private IHello iHelloWorld;

    @GetMapping("/index")
    public @ResponseBody String hello(){
        return iHelloWorld.sayHello();
    }

    @GetMapping("/redirect")
    public String redirect(){
        return "redirect:https://ctrip.com";
    }

    @GetMapping("/redirect1")
    public ModelAndView redirect1(){
        ModelAndView model = new ModelAndView();

        // 永久性重定向：301
        model.setStatus(HttpStatus.MOVED_PERMANENTLY);

        // 临时性重定向：302
        model.setStatus(HttpStatus.FOUND);

        model.setView(null);
        return model;
    }
}
