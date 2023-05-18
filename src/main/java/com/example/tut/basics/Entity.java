package com.example.tut.basics;

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

    static class Ele{
        int row, col, val;
        Ele(int row, int col, int val){
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Ele e = new Ele(1, 1, 1);
        System.out.println(e.equals(null));
    }
}
