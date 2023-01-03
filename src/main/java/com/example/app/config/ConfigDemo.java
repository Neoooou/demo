package com.example.app.config;

import com.example.app.beaninject.BeanModel;
import com.example.app.beaninject.MyFactoryBean;
import com.example.app.service.FooService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author neo.zr
 * @since 2023/1/3
 */

@Configuration
@EnableAspectJAutoProxy
public class ConfigDemo {
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
