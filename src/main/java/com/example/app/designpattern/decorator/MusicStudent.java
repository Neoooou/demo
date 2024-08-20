package com.example.app.designpattern.decorator;

public class MusicStudent extends  AbstractDecorator{
    public MusicStudent(AbstractStudent abstractStudent) {
        super(abstractStudent);
    }

    @Override
    protected String studyCourses() {
        return super.studyCourses() + "music";
    }

    public static void main(String[] args) {

    }
}
