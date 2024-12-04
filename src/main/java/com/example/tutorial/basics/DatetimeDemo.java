package com.example.tutorial.basics;

import org.apache.bcel.generic.DADD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DatetimeDemo {
    public static void main(String[] args) throws ParseException {
        // 2007-12-03T10:15:30.00Z
        String s = "2024-11-06T10:47:24.14655806Z";
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\'T\'HH:mm:ss.SSS");
//        LocalDateTime localDateTime = LocalDateTime.parse(s, dateTimeFormatter);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS");

        utcStrtoLocalDate(s, ZoneId.of("GMT+8"));
//
//        System.out.println(localDateTime);
//        System.out.println(localDateTime.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());
//        Date date = new Date();
//
//        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.of("UTC"));
//        System.out.println(localDateTime);
        for(int i = 0; i < 100; ++i){
            System.out.println(s.hashCode());
        }

    }

    final static String UTC = "UTC";

    public static Date utcStrtoLocalDate(String utcStr, ZoneId zoneId){
        Instant instant = Instant.parse(utcStr);
        System.out.println(instant.toEpochMilli());
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        System.out.println(zonedDateTime);
        Date date = Date.from(zonedDateTime.toInstant());
        System.out.println(date.getTime());
        return date;

    }

    public static ZonedDateTime toZonedDatetime(String utcStr, String zone){
        Instant instant = Instant.parse(utcStr);
        return instant.atZone(ZoneId.of(zone));
    }
}
