package com.example.app.config;

import com.example.app.beaninject.XFactoryBean;
import com.example.app.beaninject.XModel;
import com.example.app.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Bean
    WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                for (HandlerInterceptor interceptor : interceptors) {
                    registry.addInterceptor(interceptor);
                }
            }
        };
    }
}
