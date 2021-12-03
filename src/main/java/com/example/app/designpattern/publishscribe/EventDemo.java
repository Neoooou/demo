package com.example.app.designpattern.publishscribe;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/23
 */

public class EventDemo extends ApplicationEvent {
    private String message;


    public EventDemo(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
