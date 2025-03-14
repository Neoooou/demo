package com.example.app.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/22
 */

@Data
@Accessors(chain=true)
@Service
public class Employee {
    String name;
    String role;
    int age;
    boolean isLeader;
    double salary;
    byte gender;
}
