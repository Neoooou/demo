package com.example.algorithm.sort;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class MergeSort_2 implements Sort{
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        if(n <= 1) {
            return;
        }else {
            int middle = n / 2;
            int[] b = new int[middle];
            for(int i = 0; i < middle;i++) {
                b[i] = arr[i];
            }
            sort(b);

            int[] c = new int[n-middle];
            int j = 0;
            for(int i = middle; i < n;i++) {
                c[j] = arr[i];
                ++j;
            }
            sort(c);
            merge(arr,b,c);
        }
    }

    public static void merge(int[] a, int[] b, int[] c) {
        int i = 0,j = 0, k = 0;
        while(i < b.length && j < c.length) {
            if(b[i] <= c[j]) {
                a[k] = b[i];
                ++i;
            }else {
                a[k] = c[j];
                ++j;
            }
            ++k;
        }
        while(i < b.length) {
            a[k] = b[i];
            ++k;
            ++i;
        }
        while(j < c.length) {
            a[k] = c[j];
            ++k;
            ++j;
        }
    }
}
