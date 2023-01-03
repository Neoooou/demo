package com.example.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author neo.zr
 * @since 2023/1/3
 */

@Data
@Component
@ConfigurationProperties(prefix = "my")
public class PropertiesDemo {
    String name;
    int age;
    String email;
    String licensePlate;
}
