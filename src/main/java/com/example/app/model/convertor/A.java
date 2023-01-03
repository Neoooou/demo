package com.example.app.model.convertor;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.ZonedDateTime;

/**
 * @Author: neo.zr
 * @Created on: 2021/8/13
 */

@Data
public class A {
    int code;
    ZonedDateTime date;


    public static void main(String[] args) {
        A a = new A();
        a.setDate(ZonedDateTime.now());
        B b = new B();
        BeanUtils.copyProperties(a, b);
        System.out.println(b);
    }

}
