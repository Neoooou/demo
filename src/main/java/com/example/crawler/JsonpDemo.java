package com.example.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @Author: ranz
 * @Since: 2025/1/14
 */
public class JsonpDemo {

    public static void main(String[] args) throws IOException {
        String blogUrl = "https://www.viator.com/tours/Eindhoven/Self-Guided-Walking-Tour-in-Eindhoven-with-Qula-City-Trails/d24978-232749P33";
//        Document doc = Jsoup.connect(blogUrl).get();

        Connection connection = Jsoup.connect(blogUrl);
        connection.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        connection.timeout(5000);
        connection.cookie("cookiename", "val234");
        connection.cookie("cookiename", "val234");
        connection.referrer("https://www.domo.com/learn/article/understanding-competition-through-business-intelligence");
        connection.header("headersecurity", "xyz123");
        Document doc = connection.get();

        doc.select("p").forEach(System.out::println);
    }
}
