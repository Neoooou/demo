package com.example.app.config;

import com.alibaba.cobar.parser.ast.expression.primary.function.string.X;
import com.example.app.beaninject.XModel;
import com.example.app.beaninject.XFactoryBean;
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

    @Bean(name = "fooService")
    public FooService fooService1(){
        return new FooService();
    }


    @Bean
    public XModel beanModel() throws Exception {
        XFactoryBean xFactoryBean = new XFactoryBean();
        XModel model = new XModel();
        model.setAge("21");
        model.setName("neo");
        xFactoryBean.setTarget(model);
        return xFactoryBean.getObject();
    }
}
