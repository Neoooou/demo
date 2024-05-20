package com.example.tutorial;

import java.util.*;

/**
 * @author neo.zr
 * @since 2024/4/12
 */

public class A {
    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();
        m.put("a", "a");
        m.put("b", "b");
        for (Map.Entry entry : m.entrySet()) {
            if("a".equals(entry.getKey())){
                entry.setValue("aa");
            }
        }
        System.out.println(m);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        System.out.println(calendar.getTime());


        int a = 300;
        Integer aa = 300;
        System.out.println(aa.equals(a));
        System.out.println(aa == a);

        List<Long> l = new ArrayList<>();
        l.add(100L);
        l.add(200L);
        l.add(300L);
        l.add(400L);

        Long[] strs = l.stream().toArray(Long[]::new);

        System.out.println(Arrays.toString(strs));


        boolean b1 = false, b2 = true, b3 = true, b4 = false;

        if(b1 && b2 || b3){
            System.out.println(1);
        }
    }
}
