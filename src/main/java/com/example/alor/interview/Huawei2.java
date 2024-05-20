package com.example.alor.interview;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.data.relational.core.sql.In;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author neo.zr
 * @since 2023/8/27
 */

public class Huawei2 {

    static ExecutorService executorService  = Executors.newFixedThreadPool(10);

    static Integer max = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String line1 = in.nextLine();
        String line2 = in.nextLine();

        String[] line1Strs = line1.split(" ");
        int m = Integer.parseInt(line1Strs[0]), n = Integer.parseInt(line1Strs[1]);
        List<Integer> task = Arrays.stream(line2.split(" "))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        for (int i = 0; i < m; ++i) {
            final int ii = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    exec(m, task, ii);
                }
            };
            executorService.execute(runnable);
        }

        System.out.println(max);

    }

    private static void exec(int m, List<Integer> task, int i) {
        int sum = i < task.size() ? task.get(i) : 0;
        for (int j = i + m; j < task.size(); j += m) {
            sum += task.get(j);
        }
        max = Math.max(sum, max);
    }
}
