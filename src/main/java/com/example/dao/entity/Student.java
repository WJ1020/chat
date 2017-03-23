package com.example.dao.entity;

/**
 * Created by WangShiXiang on 2017/3/16.
 * 学生表
 */
public class Student {
    //学号
    private String sno;
    //姓名
    private String name;
    //性别
    private int six;
    //学院
    private String college;
    //专业
    private String major;
    //年级
    private String grade;

    public Student(){

    }
    public Student(String sno, String name, int six, String college, String major, String grade) {
        this.sno = sno;
        this.name = name;
        this.six = six;
        this.college = college;
        this.major = major;
        this.grade = grade;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
