package com.example.algorithm.sort;

import com.example.tutorial.basics.JvmParams;
import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SortTest {
    public static void main(String[] args) {
        JvmParams.printJVMOpts();

        QuickSort quick = new QuickSort();
        int len = 50_000_000;
        int[] data = nums(len);

        Stopwatch stopwatch = Stopwatch.createStarted();
        stopwatch.reset().start();
        quick.sort(data);
        System.out.println("QuickSort time elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");

        Sort merge  = new MergeSort();
        stopwatch.reset().start();
        merge.sort(data);
        System.out.println("MergeSort time elapsed " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
    }

    private static boolean judgeResult(int[] dataCopy, int[] dataSorted){
        Arrays.sort(dataCopy);
        for(int i=0; i < dataCopy.length; ++i){
            if(dataCopy[i] != dataSorted[i]){
                return false;
            }
        }
        return true;
    }

    public static int[] nums(int len){
        Random random = new Random();
        int[] nums = new int[len];
        for(int i = 0 ; i < nums.length; ++i){
            nums[i] = random.nextInt(100);
        }
        return nums;
    }
}
