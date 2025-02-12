package com.example.designpattern.publishscribe;

//import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: neo.zr
 * @Created on: 2021/6/23
 */

@Component
public class DemoListener{

//    @EventListener(classes ={EventDemo.class})
    public ReturnedEvent onApplicationEvent(EventDemo event) {
        System.out.println("received  " + event.getMessage());
        return new ReturnedEvent("returned event source");
    }

//    @EventListener(classes = {ReturnedEvent.class})
    @Async
    public void onReturnedEvent(ReturnedEvent returnedEvent){
        System.out.println("returned event source " + returnedEvent.getSource());
    }
}
