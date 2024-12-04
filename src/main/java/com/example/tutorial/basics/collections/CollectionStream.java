package com.example.tutorial.basics.collections;

import com.example.entity.Traveler;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionStream {
    public static void main(String[] args) {
        String s = "";
        System.out.println(StringUtils.isEmpty(s = "abc"));
        Traveler t1 = new Traveler().setTravelerId(1L).setPassport("PP1").setGender("Male");
        Traveler t2 = new Traveler().setTravelerId(2L).setPassport("PP2").setGender("Male");
        Traveler t3 = new Traveler().setTravelerId(3L).setPassport("PP3").setGender("Female");
        Traveler t4 = new Traveler().setTravelerId(4L).setPassport("PP4").setGender("Female");
        List<Traveler> travelerList = Lists.newArrayList(t1, t2, t3, t4);

        Map<String, List<Traveler>> travelerMap = travelerList.stream().collect(Collectors.groupingBy(Traveler::getGender));
        System.out.println(travelerMap);

        travelerMap = travelerList.stream().collect(Collectors.groupingBy(Traveler::getGender));
        System.out.println(travelerMap);


        List<String> l = Lists.newArrayList("abc", "a", "c");
        System.out.println(l.stream().anyMatch(StringUtils::isEmpty));
    }
}
