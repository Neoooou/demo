package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Traveler {
    String name;

    String gender;

    String passport;

    Long travelerId;

}
