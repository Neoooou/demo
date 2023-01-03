package com.example.app.util;

import com.example.app.model.Employee;
import com.example.app.model.Employer;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;

/**
 * @Author: Zhang Ran
 * @Date: Created on 2020/10/21
 */

public class PrintUtil {

    public static void main(String[] args) {
        Employer employer = new Employer();
        employer.setName("Jack").setRole("president").setEmployeeList(Lists.newArrayList(new Employee().setName("Mark").setRole("associate")));

        printObj(employer);
    }
    public static void printObj(Object o){
        Field[] fs = o.getClass().getDeclaredFields();
        for(Field f: fs){
            f.setAccessible(true);
            try {
                if(f.getType().isPrimitive() || f.getType().getName() == "java.lang.String"){
                    System.out.println(f.getName() + " - " + f.get(o));
                }else{
                    printObj(f.get(o));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}