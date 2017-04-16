package com.example.dao.entity;

import java.util.Date;

/**
 * Created by WangShiXiang on 2017/4/11.
 * 请假名单
 */
public class Leave {
    private int id;
    private String openid;
    //请假原因
    private String cause;
    //请假类型
    private int type;
    //请假起始时间
    private Date startDate;
    //请假结束时间
    private Date endDate;
    //请假的连续节次
    int section;
    //外出地点
    int local_1;
    //详细地址
    String local_2;
    //紧急联系人姓名
    String urgentName;
    //紧急联系人号码
    String urgentPhone;
    //请假状态
    int state;
    //辅导员openid
    String openidTeacher;

    public Leave(){}

    public Leave(String openid, String cause, int type, Date startDate, Date endDate, int section, int local_1, String local_2, String urgentName, String urgentPhone, int state) {
        this.openid = openid;
        this.cause = cause;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.section = section;
        this.local_1 = local_1;
        this.local_2 = local_2;
        this.urgentName = urgentName;
        this.urgentPhone = urgentPhone;
        this.state = state;
    }

    public Leave(int id, String openid, String cause, int type, Date startDate, Date endDate, int section, int local_1, String local_2, String urgentName, String urgentPhone, int state) {
        this.id = id;
        this.openid = openid;
        this.cause = cause;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.section = section;
        this.local_1 = local_1;
        this.local_2 = local_2;
        this.urgentName = urgentName;
        this.urgentPhone = urgentPhone;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getLocal_1() {
        return local_1;
    }

    public void setLocal_1(int local_1) {
        this.local_1 = local_1;
    }

    public String getLocal_2() {
        return local_2;
    }

    public void setLocal_2(String local_2) {
        this.local_2 = local_2;
    }

    public String getUrgentName() {
        return urgentName;
    }

    public void setUrgentName(String urgentName) {
        this.urgentName = urgentName;
    }

    public String getUrgentPhone() {
        return urgentPhone;
    }

    public void setUrgentPhone(String urgentPhone) {
        this.urgentPhone = urgentPhone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOpenidTeacher() {
        return openidTeacher;
    }

    public void setOpenidTeacher(String openidTeacher) {
        this.openidTeacher = openidTeacher;
    }
}
