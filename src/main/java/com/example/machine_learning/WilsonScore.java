package com.example.machine_learning;

public class WilsonScore {
    /**
     * 计算威尔逊得分
     * <p>
     * zP一般取值2即可，即95%的置信度。
     * </p>
     *
     * @param u  失败数
     * @param n  总数
     * @param zP 正态分布的分位数
     * @return s 威尔逊得分
     */
    public static double wilsonScore(double u, double n, double zP) {
        double p = u / n;
        System.out.println("失败率为：" + p);
        // 威尔逊得分
        double s = (p +
                (Math.pow(zP, 2) / (2. * n)) -
                ((zP / (2. * n)) *
                        Math.sqrt(4. * n * (1. - p) * p + Math.pow(zP, 2))
                )
        ) / (1. + Math.pow(zP, 2) / n);
        System.out.println("威尔逊得分为：" + s);
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i < 10000; ++i){
            System.out.print("\r"+i);
            Thread.sleep(100);
        }
    }
}
