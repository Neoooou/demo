package com.example.app.model;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: neo.zr
 * @Created on: 2021/7/5
 */

public class MyFactoryBean implements FactoryBean {

    private MyFactoryBean target;

    public void setTarget(MyFactoryBean target){
        this.target = target;
    }

    public MyFactoryBean(){
        System.out.println("init by constructor");
    }

    @Override
    public Object getObject() throws Exception {
        return target;
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

}
