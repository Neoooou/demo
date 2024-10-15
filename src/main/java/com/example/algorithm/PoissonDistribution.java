package com.example.algorithm;

public class PoissonDistribution {


    public static double calculateProbability(double lambda, int k){
        double numerator = Math.exp(-lambda) * Math.pow(lambda, k);
        double denominator = factorial(k);
        return numerator / denominator;
    }

    public static double factorial(int n){
        double result = 1D;
        for(int i = 1; i < n; ++i){
            result *= i;
        }
        return result;
    }
}
