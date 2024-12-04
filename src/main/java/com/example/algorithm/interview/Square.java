package com.example.algorithm.interview;

/**
 * @author neo.zr
 * @since 2024/1/25
 */

public class Square {

    public static void main(String[] args) {

    }

    /**
     * 二分开根号
     */
    private Double square(double d, int scale) {
        return null;
    }

    public int mySqrt(int x) {
        if(x < 1){
            return x;
        }

        int left = 1, right = x;
        long mid;
        while(left <= right){
            mid = (right - left) / 2 + left;
            if(mid * mid < x){
                left = (int)mid + 1;
            }else if(mid * mid > x){
                right = (int)mid-1;
            }else{
                return (int)mid;
            }
        }
        return 1;
    }

}
