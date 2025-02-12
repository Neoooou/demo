package com.example.designpattern.decorator;

public abstract class AbstractDecorator extends AbstractStudent{

    private AbstractStudent abstractStudent;

    public AbstractDecorator(AbstractStudent abstractStudent){
        this.abstractStudent = abstractStudent;
    }
    @Override
    protected String studyCourses() {
        return abstractStudent.studyCourses();
    }
}
