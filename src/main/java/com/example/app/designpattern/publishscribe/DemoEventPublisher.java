package com.example.app.designpattern.publishscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/23
 */

@Component
public class DemoEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doPublish(final String message){
        EventDemo event = new EventDemo(this, message);
        applicationEventPublisher.publishEvent(event);
        System.out.println("Sent " + message);
    }
}
