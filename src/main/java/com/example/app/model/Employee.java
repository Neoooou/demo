package com.example.app.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/22
 */

@Data
@Accessors(chain=true)
public class Employee {
    String name;
    String role;
}
