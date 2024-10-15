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
        System.out.println(Objects.equals(null, 1L));
        System.out.println(Objects.equals(1L, 1L));
        System.out.println(Objects.equals(null, null));
        Traveler t1 = new Traveler().setTravelerId(1L).setPassport("PP1").setGender("Male");
        Traveler t2 = new Traveler().setTravelerId(2L).setPassport("PP2").setGender("Male");
        Traveler t3 = new Traveler().setTravelerId(3L).setPassport("PP3").setGender("Female");
        Traveler t4 = new Traveler().setTravelerId(4L).setPassport("PP4").setGender("Female");
        List<Traveler> travelerList = Lists.newArrayList(t1, t2, t3, t4);

        Map<String, List<Traveler>> travelerMap = travelerList.stream().collect(Collectors.groupingBy(Traveler::getGender));
        System.out.println(travelerMap);

        HashMap<String, Integer> intMap = Maps.newHashMap();
        intMap.put(null, 1);
        System.out.println(intMap.get(null));

        t1.setGender(null);
        travelerMap = travelerList.stream().collect(Collectors.groupingBy(Traveler::getGender));
        System.out.println(travelerMap);
    }
}
