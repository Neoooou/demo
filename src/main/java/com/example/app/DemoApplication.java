package com.example.app;

import com.example.app.advice.annotations.EnableCustomBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;


@SpringBootApplication(scanBasePackages = {"com.example.app"})
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        DataSourceAutoConfiguration.class,

        HealthEndpointAutoConfiguration.class,
        MetricsAutoConfiguration.class
})
@EnableCustomBean(basePackages = {"com.example.acl"})
public class DemoApplication {

    public static void main(String[] args) throws JsonProcessingException {
//        PandoraBootstrap.run(args);
//        ConfigurableApplicationContext context =
//                new SpringApplicationBuilder(DemoApplication.class).run(args);
//        PandoraBootstrap.markStartupAndWait();


        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    }

}
