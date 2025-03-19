package com.example.app.annotation;

import com.example.app.beaninject.ABeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 通过注解导入Bean
 *
 * @Author: neo.zr
 * @Created on: 2021/11/23
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ABeanDefinitionRegistrar.class)
public @interface EnableCustomBean {
    String[] basePackages() default {};
}
