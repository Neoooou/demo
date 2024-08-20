package com.example.algorithm.sort;

import com.example.tutorial.basics.JvmParams;
import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SortTest {
    public static void main(String[] args) {
        Sort merge  = new MergeSort_1();

        JvmParams.printJVMOpts();

        Sort quick = new QuickSort();
        int len = 100_000_000;
        int[] data = nums(len);

        Stopwatch stopwatch = Stopwatch.createStarted();
//        merge.sort(data);
//        System.out.println("MergeSort time elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
//        System.gc();

//        data = nums(len);
        stopwatch.reset().start();
        quick.sort(data);
        System.out.println("QuickSort time elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");

//        data = nums(len);
//        stopwatch.reset().start();
//        quick.bubbleSort(data);
//        System.out.println("BubbleSort time elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
//
//        data = nums(len);
//        stopwatch.reset().start();
//        quick.selectSort(data);
//        System.out.println("SelectSort time elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
//
//        data = nums(len);
//        stopwatch.reset().start();
//        quick.insertSort(data);
//        System.out.println("InsertSort time elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }

    public static int[] nums(int len){
        Random random = new Random();
        int[] nums = new int[len];
        for(int i = 0 ; i < nums.length; ++i){
            nums[i] = random.nextInt(nums.length);
        }
        return nums;
    }
}
