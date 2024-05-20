package com.example.alor.sort;

import java.util.Arrays;
import java.util.Collections;

/**
 *  time complexity: 最好最坏平均 均为O(nlog(n))
 *  space complexity: O(nlog(n))
 * @author neo.zr
 * @since 2023/4/4
 */

public class MergeSort implements Sort{
    @Override
    public void sort(int[] arr) {
        int len = arr.length;
        if(len<=1) return;
        else {
            int[] left = Arrays.copyOfRange(arr, 0, len/2);
            int[] right = Arrays.copyOfRange(arr, len/2,len);
            sort(left);
            sort(right);
            merge(left,right,arr);
        }
    }

    private void merge(int[] left, int[] right, int[] arr) {
        int i = 0, j = 0 , k = 0;
        while(i< left.length && j < right.length) {
            if(left[i] >= right[j]) {
                arr[k] = right[j];
                ++j;
            }else {
                arr[k] = left[i];
                ++i;
            }
            ++k;
        }
        while(i < left.length) {
            arr[k] = left[i];
            ++i;
            ++k;
        }
        while(j < right.length) {
            arr[k] = right[j];
            ++j;
            ++k;
        }
    }
}
