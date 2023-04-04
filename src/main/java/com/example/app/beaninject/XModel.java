package com.example.app.beaninject;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 *
 * bean加载顺序：
 * init BeanFactoryPostProcessor
 * -> BeanFactoryPostProcessor#postProcessBeanFactory
 * -> init BeanPostProcessor
 * ->
 * @Author: neo.zr
 * @Created on: 2021/12/14
 */

@Data
public class XModel implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware {
    String name;
    String age;

    private String beanName;//实现了BeanNameAware接口，Spring可以将BeanName注入该属性中
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware的setBeanFactory方法得到beanFactory引用");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("【BeanNameAware接口】调用BeanNameAware的setBeanName方法得到Bean的名称");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【DisposableBean接口】调用DisposableBean接口的destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【InitializingBean接口】调用InitializingBean接口的afterPropertiesSet方法");
    }
}
