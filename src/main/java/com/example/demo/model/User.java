package com.example.demo.model;

import lombok.Getter;
import org.springframework.beans.factory.BeanNameAware;

/**
 * get bean name
 * @Author: neo.zr
 * @Created on: 2021/7/5
 */

@Getter
public class User implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
