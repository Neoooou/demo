package com.example.entity;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: neo.zr
 * @since: 2021/4/15
 */

@AllArgsConstructor
@Data
public class Entity {
    @NonNull
    Integer id;

    String name;

    transient Double salary;

    static transient Map<String, String> map = new HashMap<String, String>(){{
        String s = "abcd";
        for(int i = 0; i < s.length(); ++i){
            put(s.charAt(i) + "", Strings.repeat(s.charAt(i)+"", 10));
        }
    }};

    static String department = "Lazada";

    public static void main(String[] args) {
        int i = 1;
        do{
            System.out.println("do.");
        }while (++i < 3);
    }
}
