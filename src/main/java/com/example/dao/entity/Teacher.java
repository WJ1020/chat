package com.example.dao.entity;

/**
 * Created by WangShiXiang on 2017/3/26.
 * 教师表的实体类
 */
public class Teacher {
    private int id;
    private String openid;
    private String name;//姓名
    private String college;//学院

    public Teacher(int id, String openid, String name, String college) {
        this.id = id;
        this.openid = openid;
        this.name = name;
        this.college = college;
    }

    public Teacher(String openid, String name, String college) {
        this.openid = openid;
        this.name = name;
        this.college = college;
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
}
