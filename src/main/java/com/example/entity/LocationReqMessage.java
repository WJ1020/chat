package com.example.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 地理位置消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class LocationReqMessage {
    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MsgType;
    private double Location_X;
    private double Location_Y;
    private int Scale;
    private String Label;
    private long MsgId;

    public LocationReqMessage(String toUserName, String fromUserName, int createTime, String msgType, double location_X, double location_Y, int scale, String label, long msgId) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Location_X = location_X;
        Location_Y = location_Y;
        Scale = scale;
        Label = label;
        MsgId = msgId;
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

    public String getMsgType() {
        return MsgType;
    }

    public double getLocation_X() {
        return Location_X;
    }

    public double getLocation_Y() {
        return Location_Y;
    }

    public int getScale() {
        return Scale;
    }

    public String getLabel() {
        return Label;
    }

    public long getMsgId() {
        return MsgId;
    }
}
