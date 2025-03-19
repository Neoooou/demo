package com.example.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


@SpringBootApplication(scanBasePackages = {"com.example.app"})
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
})
public class Application {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(Application.class, args);
    }

}
