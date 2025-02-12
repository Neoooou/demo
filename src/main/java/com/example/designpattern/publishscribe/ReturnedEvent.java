package com.example.designpattern.publishscribe;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: neo.zr
 * @Created on: 2021/7/5
 */

public class ReturnedEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ReturnedEvent(Object source) {
        super(source);
    }
}
