package com.example.tutorial.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Author: ranz
 * @Since: 2025/1/10
 */
public class SchedulerExecutorDemo {
//    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder().setNameFormat("pool-%d").build());
//
//    public static void main(String[] args) {
//        scheduledExecutorService.scheduleWithFixedDelay(new Task(1), 10, 5, TimeUnit.SECONDS);
//    }
//
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> getValueHandle;

    public void retryForUpToThreeSeconds() {
        final Runnable getValue = new Runnable() {
            public void run() {
                String value = getValue();
                System.out.println(" Current time : "
                        + Calendar.getInstance().get(Calendar.SECOND));
                if (!value.equals("")) getValueHandle.cancel(false);

            }
        };

        getValueHandle = scheduler.scheduleAtFixedRate(getValue, 5, 5, SECONDS);

        scheduler.schedule(new Runnable() {
            public void run() {
                getValueHandle.cancel(false);
            }
        }, 30, SECONDS);
    }

    private String getValue() {
        return new Random().nextInt(5) == 2 ? "1" : "";
    }

    public static void main(String[] args) {
        SchedulerExecutorDemo demo = new SchedulerExecutorDemo();
        demo.retryForUpToThreeSeconds();
    }
}

class Task implements Runnable {
    private int num;

    public Task(int num) {
        this.num = num;
    }

    public void run() {
        System.out.println(
                "Number " + num + " Current time : "
                        + Calendar.getInstance().get(Calendar.SECOND));
    }
}
