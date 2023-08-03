package com.example.app;

import com.alibaba.boot.acl.config.AclAutoConfiguration;
import com.alibaba.fastjson.JSON;
import com.alibaba.global.g11n.model.CurrencyUnit;
import com.alibaba.global.money.Money;
import com.example.app.annotations.EnableCustomBean;
import com.example.app.beaninject.XModel;
import com.example.app.service.FooService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.taobao.pandora.boot.PandoraBootstrap;
import org.springframework.boot.actuate.autoconfigure.health.HealthEndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.RoundingMode;
import java.util.Map;


@SpringBootApplication(scanBasePackages = {"com.example.app"})
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        AclAutoConfiguration.class,
        HealthEndpointAutoConfiguration.class,
        MetricsAutoConfiguration.class
})
@EnableCustomBean(basePackages = {"com.example.acl"})
public class DemoApplication {

    public static void main(String[] args) throws JsonProcessingException {
        PandoraBootstrap.run(args);
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(DemoApplication.class).run(args);
        PandoraBootstrap.markStartupAndWait();
    }

}
