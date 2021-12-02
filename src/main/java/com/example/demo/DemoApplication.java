package com.example.demo;

import com.alibaba.boot.acl.config.AclAutoConfiguration;
import com.example.demo.annotations.EnableCustomBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.taobao.pandora.boot.PandoraBootstrap;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        AclAutoConfiguration.class
})
@EnableCustomBean(basePackages = {"com.example.acl"})
public class DemoApplication {

    public static void main(String[] args) throws JsonProcessingException {
        PandoraBootstrap.run(args);
        PandoraBootstrap.markStartupAndWait();
    }

}
