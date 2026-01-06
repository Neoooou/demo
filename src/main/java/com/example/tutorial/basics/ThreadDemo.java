package com.example.tutorial.basics;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Locale;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: neo.zr
 * @since: 2021/3/19
 */

public class ThreadDemo {

    /**
     * thread local variables
     */
    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();


    private static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 4;

    private static long KEEP_ALIVE_TIME = 600L;

    private static int capacity = 4028;

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExecutorUtil-%d").build();

    private static final ThreadPoolExecutor COMMON_THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(capacity),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy() //

    );

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread 1 current value: " + threadLocal.get());
                    threadLocal.set(10);
                    Thread.sleep(500);
                    System.out.println("Thread 1 current value: " + threadLocal.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread 2 current value: " + threadLocal.get());
                    threadLocal.set(20);
                    Thread.sleep(500);
                    System.out.println("Thread 2 current value: " + threadLocal.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


        String s = "- Scenario:\n" +
                "You're a customer support specialist for an e-commerce platform, tasked with summarizing chat conversations with buyers and sellers.\n" +
                "\n" +
                "-  Instructions:\n" +
                "Craft a bullet-point summary of the chat conversation focusing on the situation, action, and resolution. Carefully distinguish the behavior of customers and agents, and carefully distinguish whether events are caused by customers, agents, or side effects of other events. Ensure that the generated summary is logical and free from grammatical errors. Limit your summary to 3 key sentences, one for each aspect, within a 70-word total. \n" +
                "\n" +
                "- Format:\n" +
                "Begin each point with \"S\" for Situation, \"A\" for Action, or \"R\" for Resolution. Each should be followed by a hyphen and a succinct statement. Ensure each statement provides unique information and avoid repetition.\n" +
                "\n" +
                "- Content Assessment:\n" +
                "Before summarizing, assess if the conversation contains enough information to extract a clear situation, action, and resolution.\n" +
                "\n" +
                "- If sufficient information is present, proceed with the summary.\n" +
                "- If the conversation lacks the necessary detail for generating summary, Conversation information is insufficient to generate a summary.\n" +
                "- Summary Content:\n" +
                "\"S\" - Concisely state the reason for the customer's contact and their tone.\n" +
                "\"A\" - Describe the precise measures customer support took to address the issue.\n" +
                "\"R\" - Convey the final outcome and the customer's reaction to the resolution.\n" +
                "\n" +
                "- Language and Tone:\n" +
                "Use precise, active language. Eliminate redundancy and maintain an objective tone without attributing any qualities or judgments to the participants.Distinguish whether the customer is a buyer or a seller based on the context.\n" +
                "\n" +
                "- Example of the desired output format:\n" +
                "If sufficient information, reply in a JSON format:\n" +
                "{\n" +
                "    \"S\" : \"Customer inquired about a missing shipment with urgency.\",\n" +
                "    \"A\" : \"Agent checked the tracking details and confirmed a carrier delay.\",\n" +
                "    \"R\" : \"Informed the customer about the delay; customer opted to wait for the shipment.\"\n" +
                "}\n" +
                "If insufficient information:\n" +
                "Conversation information is insufficient to generate a summary.\n" +
                "\n" +
                "Now I will provide you with the conversation and please help me summarize it and provide the summary following the output format.\n" +
                "Here is the conversation:%s. Please generate the summary in %s";

        System.out.println(String.format(s, "ppppp", "OOO"));

        System.out.println(Locale.forLanguageTag("en-MY"));
    }
}
