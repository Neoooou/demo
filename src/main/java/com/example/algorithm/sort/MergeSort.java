package com.example.algorithm.sort;

import java.util.Arrays;

/**
 * @author neo.zr
 * @since 2023/4/4
 * time complexity: 最好最坏平均 均为O(nlog(n))
 * space complexity: O(nlog(n))
 * 排序稳定，不打乱原有的排序
 */

public class MergeSort implements Sort{
    @Override
    public void sort(int[] arr) {
        sort1(arr);
    }

    public void sort1(int[]  arr){
        int len = arr.length;
        if(len<=1) return;
        else {
            int[] left = Arrays.copyOfRange(arr, 0, len/2);
            int[] right = Arrays.copyOfRange(arr, len/2,len);
            sort1(left);
            sort1(right);
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


    private int[] numbers;
    private int[] helper;
    /**
     * 使用额外Helper数组，减小空间复杂度
     * @param arr
     */
    public void sortWithHelper(int[] arr) {
        this.numbers = arr;
        this.helper = new int[arr.length];
        sortWithHelper(0, arr.length - 1);
    }

    private void sortWithHelper(int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            sortWithHelper(low, middle);
            // Sort the right side of the array
            sortWithHelper(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {
        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.

    }
}
