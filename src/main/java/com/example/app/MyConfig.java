package com.example.app;

import com.example.app.beaninject.BeanModel;
import com.example.app.beaninject.MyFactoryBean;
import com.example.app.service.FooService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/9
 */

@Configuration
@EnableAspectJAutoProxy
public class MyConfig {

    @Bean
    public MyFactoryBean myFactoryBean(){
        return new MyFactoryBean();
    }

    @ConditionalOnMissingBean(name="fooService2")
    @Bean(name = "fooService1")
    public FooService fooService(){
        return new FooService();
    }

    @Bean(name = "fooService")
    public FooService fooService1(){
        return new FooService();
    }

    @ConditionalOnBean(name = "fooService")
    @Bean(name = "fooService2")
    public FooService fooService2(){
        return new FooService();
    }

    @Bean
    public BeanModel beanModel(){
        BeanModel model = new BeanModel();
        model.setAge("22");
        model.setName("neo");
        return model;
    }
}


