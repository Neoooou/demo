package com.example.tutorial;

import com.example.algorithm.leetcode.LRUCache;
import com.example.app.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.data.mongodb.core.aggregation.DateOperators;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class GsonDemo {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Employee employee = new Employee();
        employee.setName("Neo");
        employee.setRole("Dev");
        employee.setAge(20);
        employee.setGender((byte)1);
        employee.setSalary(100.05);
        employee.setLeader(false);
        String jsonString = gson.toJson(employee);
        System.out.println(jsonString);

        JsonObject object = gson.fromJson(jsonString, JsonObject.class);
        System.out.println(object.get("age").toString());
        System.out.println(object.get("gender").getAsString());
        System.out.println(object.get("salary").getAsString());
        System.out.println(object.get("isLeader").getAsString());

        TimeZone zone = TimeZone.getTimeZone("Europe/Paris");
        System.out.println(zone.useDaylightTime());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        System.out.println("Date: " + calendar.getTime());
        System.out.println("inDayLightTime: " + zone.inDaylightTime(calendar.getTime()));

        try{
            String s = "ab";
            for(int i = 0; i < 32; ++i){
                s = s + s;
                System.out.println(i + " - " + s.length());
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
