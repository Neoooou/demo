package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: neo.zr
 * @Created on: 2021/8/21
 */
@Data
@Component
@ConfigurationProperties(prefix = "my")
public class MyProperties {
    String name;
    int age;
    String email;
    String licensePlate;
}
