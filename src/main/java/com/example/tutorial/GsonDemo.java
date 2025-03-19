package com.example.tutorial;

import com.example.algorithm.leetcode.LRUCache;
import com.example.app.model.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.data.mongodb.core.aggregation.DateOperators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GsonDemo {
    public static void main(String[] args) {
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
//        Gson gson = builder.create();
//
//        Employee employee = new Employee();
//        employee.setName("Neo");
//        employee.setRole("Dev");
//        employee.setAge(20);
//        employee.setGender((byte) 1);
//        employee.setSalary(100.05);
//        employee.setLeader(false);
//        String jsonString = gson.toJson(employee);
//        System.out.println(jsonString);
//
//        JsonObject object = gson.fromJson(jsonString, JsonObject.class);
//        System.out.println(object.get("age").toString());
//        System.out.println(object.get("gender").getAsString());
//        System.out.println(object.get("salary").getAsString());
//        System.out.println(object.get("isLeader").getAsString());
//
        String timezoneStr = "Europe/Paris";
        TimeZone zone = TimeZone.getTimeZone(timezoneStr);
//        System.out.println(timezoneStr + " useDaylightTime=" + zone.useDaylightTime());
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 2024);
//        calendar.set(Calendar.MONTH, 9);
//        calendar.set(Calendar.DAY_OF_MONTH, 26);
//        calendar.set(Calendar.HOUR, 23);
//        Date date = calendar.getTime();
//        System.out.println("Date: " + date);
//        date = toSpecificZone(date, zone);

//        System.out.println("transferred date:" + date);
//        System.out.println("inDayLightTime: " + zone.inDaylightTime(date));
        Date date = new Date();
        System.out.println(date);
//        toSpecificZone(, zone);


        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson("{}", JsonObject.class);
        JsonElement element = jsonObject.get("a");
        System.out.println(element.getAsString());

    }

    private static Date toSpecificZone(Date date, TimeZone timeZone){
        try{
            System.out.println(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(timeZone);
            String zoneDateStr = sdf.format(date);
            Date rst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zoneDateStr);
            System.out.println(rst);
            return rst;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }


}
