package com.example.dao.entity;

import java.util.Date;

/**
 * Created by WangShiXiang on 2017/3/18.
 * 缺课信息
 */
public class Absences {
    private int id;
    //学生信息(数据库中储存得是该学生得学号，即学生表的主键是此表的外键)
    private Student student;
    //课程(此处应该储存课程的唯一编号)
    private String course;
    //时间
    private Date date;
    //状态（缺课请假迟到）
    private int state;

    public Absences(Student student, String course, Date date, int state) {
        this.student = student;
        this.course = course;
        this.date = date;
        this.state = state;
    }
    public Absences(){}

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
