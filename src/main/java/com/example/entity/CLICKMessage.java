package com.example.entity;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 自定义点击事件
 */
public class CLICKMessage {
    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MsgType;
    private String Event;
    private String EventKey;

    public CLICKMessage(String toUserName, String fromUserName, int createTime, String msgType, String event, String eventKey) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Event = event;
        EventKey = eventKey;
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

    public String getEventKey() {
        return EventKey;
    }
}
