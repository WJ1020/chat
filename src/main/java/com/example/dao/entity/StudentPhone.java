package com.example.dao.entity;

/**
 * Created by WangShiXiang on 2017/4/9.
 * 用来储存学生的手机号
 */
public class StudentPhone {
    private int id;
    private String openid;
    private String sno;
    private String phone;
    private String state;


    public StudentPhone(){}

    public StudentPhone(int id, String openid, String sno, String phone, String state) {
        this.id = id;
        this.openid = openid;
        this.sno = sno;
        this.phone = phone;
        this.state = state;
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

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
