package com.example.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 关注消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class FollowMessage {
    private String ToUserName;
    private String FromUserName;
    private int  CreateTime;
    private String MsgType;
    private String Event;

    public FollowMessage(String toUserName, String fromUserName, int createTime, String msgType, String event) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Event = event;
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

    public String getEvent() {
        return Event;
    }
}
