package com.example.app.model.convertor;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: neo.zr
 * @Created on: 2021/8/13
 */

public enum AEnum {
    A(11),
    B(22);

    @Getter
    @Setter
    int code;

    AEnum(int c){
        this.code = c;
    }
}
