package com.example.designpattern.decorator;

public class ComputerScienceStudent extends AbstractDecorator{
    public ComputerScienceStudent(AbstractStudent abstractStudent){
        super(abstractStudent);
    }

    @Override
    protected String studyCourses() {
        return super.studyCourses() + "Computer Science";
    }
}
