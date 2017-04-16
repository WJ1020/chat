package com.example.dao.entity;

/**
 * Created by WangShiXiang on 2017/4/14.
 * 辅导员
 */
public class Mentor {
    private int id;
    private String openid;
    private String name;
    //专业
    private String major;
    //年级
    private String grade;
    //手机号
    private String phone;

    public Mentor(int id, String openid, String name, String major, String grade, String phone) {
        this.id = id;
        this.openid = openid;
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.phone = phone;
    }
    public Mentor(){}
    public Mentor(String openid, String name, String major, String grade, String phone) {
        this.openid = openid;
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
