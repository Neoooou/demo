package com.example.alor.sort;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public interface Sort {

    /**
     * bubble sort
     * time complexity: O(n*n)
     * 稳定排序
     */
     default void bubbleSort(int[] arr) {
        for(int i = 0 ;i < arr.length; i++) {
            for(int j = 0; j < arr.length-i-1; ++j) {
                if(arr[j] > arr[j+1]) {
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                }
            }
        }
    }

    /**
     *  insert sort
     *  time complexity: O(n*n)
     *  对局部有序的数组排列较快，减少比较和交换的次数
     *  稳定排序
     */
    default void insertSort(int[] arr) {
        int j;
        for(int i = 1; i < arr.length; ++i) {
            int temp = arr[i];
            for( j= i; j > 0 && temp < arr[j-1]; --j) {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }

    /**
     * select sort
     * time complexity: O(n*n)
     * 不稳定排序
     */
    default void selectSort(int[] arr){
        if(arr == null || arr.length ==0) return;
        final int len = arr.length;
        for(int i = 0; i < len ; ++i) {
            int minPtr = i;
            for(int j = i+1; j < len; ++j) {
                if(arr[j] < arr[minPtr]) minPtr = j;
            }
            int temp = arr[i];
            arr[i] = arr[minPtr];
            arr[minPtr] = temp;
        }
    }


    void sort(int[] arr);
}
