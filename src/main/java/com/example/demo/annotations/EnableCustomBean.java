package com.example.demo.annotations;

import com.example.demo.MyBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/23
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyBeanDefinitionRegistrar.class)
public @interface EnableCustomBean {
    String[] basePackages() default {};
}
