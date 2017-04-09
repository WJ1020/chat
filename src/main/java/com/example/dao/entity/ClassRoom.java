package com.example.dao.entity;

/**
 * Created by WangShiXiang on 2017/4/7.
 * 教室实体
 */
public class ClassRoom {

    private int id;
    //教室编号
    private String number;
    //类型 普通教室0，机房1，阶梯教室2
    private int type;
    //容量
    private int seating;
    //当前状态，
    private int state;

    public ClassRoom(int id, String number, int type, int seating, int state) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.seating = seating;
        this.state = state;
    }

    public ClassRoom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSeating() {
        return seating;
    }

    public void setSeating(int seating) {
        this.seating = seating;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return getNumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null){
            return false;
        }else if(this.getNumber().equals(obj.toString())){
            return true;
        }else {
            return false;
        }
    }
}
