package com.example.app.beaninject;

import org.springframework.beans.factory.FactoryBean;

/**
 * A bean
 * @Author: neo.zr
 * @Created on: 2021/7/5
 */

public class XFactoryBean implements FactoryBean<XModel> {

    private XModel target;

    public void setTarget(XModel target){
        this.target = target;
    }

    public XFactoryBean(){
        System.out.println("init by constructor");
    }

    @Override
    public XModel getObject() throws Exception {
        return target;
    }

    @Override
    public Class<?> getObjectType() {
        return XModel.class;
    }

}
