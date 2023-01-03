package com.example.app.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/22
 */

@Data
@Accessors(chain=true)
public class Employer {
    String name;
    String role;
    List<Employee> employeeList;
}
