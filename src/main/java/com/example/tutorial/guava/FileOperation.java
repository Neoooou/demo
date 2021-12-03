package com.example.tutorial.guava;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author: neo.zr
 * @Created on: 2021/11/3
 */

public class FileOperation {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readLines(new File("test.txt"), Charset.defaultCharset());
    }
}
