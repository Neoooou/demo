package com.example.app.beaninject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: neo.zr
 * @Created on: 2021/12/14
 */

@Component
public class ABeanPostProcessor implements BeanPostProcessor {
    public ABeanPostProcessor(){
        System.out.println("call MyBeanPostProcessor non-arg constructor");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
