package com.example.algorithm.sort;

/**
 * @author neo.zr
 * @since 2023/4/4
 * 基于哨兵划分操作，比较数组元素与哨兵元素的大小，将其划分为左右两个数组（大和小）
 *
 * 缓存友好，局部元素之间进行比较和交换，符合CPU多级设计的空间局部性
 * 交换和比较次数相对归并更少.
 */

public class QuickSort implements Sort{
    @Override
    public void sort(int[] arr) {
        sort(arr,arr.length);
    }

    private void sort(int[] arr,int len) {
        if(len <= 1) return;
        else {
            int index = len/2;
            int ele = arr[index];
            int[] helper0 = new int[len];//store smaller-value ele
            int[] helper1 = new int[len];//store same-value ele
            int[] helper2 = new int[len];//store bigger-value ele
            int j =0, k = 0, l = 0;
            for(int i = 0; i < len; ++i) {
                int temp = arr[i];
                if(temp < ele) {
                    helper0[j] = temp;
                    ++j;
                }else if(temp == ele){
                    helper1[k] = temp;
                    ++k;
                }else {
                    helper2[l] = temp;
                    ++l;
                }
            }
            sort(helper0,j);
            sort(helper2,l);
            for(int i = 0; i < j; ++i) {
                arr[i] = helper0[i];
            }
            for(int i = 0; i < k; ++i) {
                arr[j+i] = helper1[i];
            }
            for(int i = 0; i < l; ++i) {
                arr[j+k+i] = helper2[i];
            }
        }
    }
}
