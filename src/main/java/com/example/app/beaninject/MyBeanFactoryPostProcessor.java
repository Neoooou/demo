package com.example.app.beaninject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: neo.zr
 * @Created on: 2021/12/14
 */

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    MyBeanFactoryPostProcessor(){
        System.out.println("call MyBeanFactoryPostProcessor non-arg constructor..");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("call MyBeanFactoryPostProcessor#postProcessBeanFactory ..");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("beanModel");
        beanDefinition.getPropertyValues().addPropertyValue("age", "21");
    }
}
