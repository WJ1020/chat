package com.example.dao.entity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/11.
 * 微信网页授权获取的信息
 */
public class SNSUserInfo {
    //用户标识
    private String openid;
    //用户昵称
    private String nickname;
    //性别(1是男，2是女，0位置)
    private int sex;
    //省份
    private String province;

    //城市
    private String city;
    //国家
    private String country;

    //用户头像链接
    private String headimgurl;
    //用户特权信息
    private List<String> privilege;

    private String unionid;

    public SNSUserInfo(){}

    public SNSUserInfo(String openid, String nickname, int sex, String province, String city, String country, String headimgurl, List<String> privilege, String unionid) {
        this.openid = openid;
        this.nickname = nickname;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.country = country;
        this.headimgurl = headimgurl;
        this.privilege = privilege;
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
