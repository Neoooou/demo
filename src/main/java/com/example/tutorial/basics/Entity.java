package com.example.tutorial.basics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

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

    Double salary;
}
