package com.example.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 回溯
 *  找执行逻辑之间关系， 停止条件
 *  大化小，小化了
 * @author neo.zr
 * @since 2023/8/28
 */

public class BackTrack {
    final static String LEFT_PARENTHESES = "(";
    final static String RIGHT_PARENTHESES = ")";


    private static int binarySearch(int[] arr, int target){
        int left = 0, right = arr.length-1, mid;
        while(left <= right){
            mid = (right- left) / 2 + left;
            if(arr[mid] > target){
                right = mid;
            }else if(arr[mid] < target){
                left = mid + 1;
            }else{
                return mid;
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        System.out.println(new BackTrack().generateParenthesis(3));

        Random random = new Random();
        int[] arr = new int[100];
        for(int i =0; i<arr.length; ++i){
            arr[i] = random.nextInt(10);
        }
        Arrays.sort(arr);

        int[] targets = new int[10];
        for(int i=0; i<targets.length; ++i){
            targets[i]=random.nextInt(10);
        }

        System.out.println(Arrays.toString(arr));
        for(int target: targets){
            System.out.println(target);
            int ans = Arrays.binarySearch(arr, target);
            int ans1 = binarySearch(arr, target);

            System.out.println(ans + " - " + ans1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> candidates = new LinkedList<>();
        backtrack(candidates, "", 0, 0, n);
        return candidates;
    }

    public void backtrack(List<String> candidates, String s, int left, int right, int n) {
        if (s.length() == 2 * n) {
            candidates.add(s);
            return;
        }
        if (left < n) {
            backtrack(candidates, s + LEFT_PARENTHESES, left + 1, right, n);
        }
        if (right < left) {
            backtrack(candidates, s + RIGHT_PARENTHESES, left, right + 1, n);
        }
    }
}
