package com.example.demo.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author: neo.zr
 * @since: 2021/1/20
 */

@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TestAlias {

    @AliasFor
    String value() default "";

    @AliasFor
    String name() default "";
}
