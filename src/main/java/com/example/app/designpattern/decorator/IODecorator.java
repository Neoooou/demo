package com.example.app.designpattern.decorator;

import com.example.app.model.convertor.B;

import java.io.*;
import java.util.Objects;

public class IODecorator {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("a.txt"));

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("a.text"));

        ObjectInputStream objectInputStream1 = new ObjectInputStream(new ByteArrayInputStream(new byte[1024]));


    }
}
