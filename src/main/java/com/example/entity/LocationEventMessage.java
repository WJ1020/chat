package com.example.entity;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 上报的地理事件
 */
public class LocationEventMessage {
    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MsgType;
    private String Event;
    private double Latitude;
    private double Longitude;
    private double Precision;

    public LocationEventMessage(String toUserName, String fromUserName, int createTime, String msgType, String event, double latitude, double longitude, double precision) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Event = event;
        Latitude = latitude;
        Longitude = longitude;
        Precision = precision;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public String getEvent() {
        return Event;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public double getPrecision() {
        return Precision;
    }

    public String getMsgType() {
        return MsgType;
    }
}
