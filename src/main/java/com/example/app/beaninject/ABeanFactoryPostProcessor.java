package com.example.app.beaninject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * operate on the metadata level
 * @Author: neo.zr
 * @Created on: 2021/12/14
 */

@Component
public class ABeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("beanModel");

        /**
         * 修改Bean属性, dependsOn、Scope等Spring属性
         */
        beanDefinition.getPropertyValues().addPropertyValue("age", "21");
    }
}
