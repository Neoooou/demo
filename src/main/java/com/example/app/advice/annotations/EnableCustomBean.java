package com.example.app.advice.annotations;

import com.example.app.beaninject.ABeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
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
