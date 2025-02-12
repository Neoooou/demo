package com.example.designpattern.decorator;

public class TestDecorator {
    public static void main(String[] args) {
        CollegeStudent collStu = new CollegeStudent();

        MiddleSchoolStudent midStu = new MiddleSchoolStudent();
        System.out.println( collStu.studyCourses());

        AbstractStudent computerStudent = new ComputerScienceStudent(collStu);
        System.out.println( computerStudent.studyCourses());

        AbstractStudent musicStu = new ComputerScienceStudent(collStu);
        System.out.println( musicStu.studyCourses());

        computerStudent = new ComputerScienceStudent(midStu);
        System.out.println( computerStudent.studyCourses());

        musicStu = new ComputerScienceStudent(midStu);
        System.out.println( musicStu.studyCourses());

    }
}
