package com.example.dao.entity;

import java.util.Date;

/**
 * Created by WangShiXiang on 2017/3/26.
 *
 */
public class Course {
    private int id;
    //任课教师的openid
    private String openid;
    //课程名
    private String name;
    //学院
    private String college;
    //专业
    private String major;
    //年级
    private String grade;
    //任课教师姓名
    private String teacherName;
    //节次(默认一天四节课，从周一开始计数到20)
    private int count;
    //上课地点
    private String locale;

    public Course(int id, String openid, String name, String college, String major, String grade, String teacherName, int count, String locale) {
        this.id = id;
        this.openid = openid;
        this.name = name;
        this.college = college;
        this.major = major;
        this.grade = grade;
        this.teacherName = teacherName;
        this.count = count;
        this.locale = locale;
    }

    public Course(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return getLocale();
    }
}
