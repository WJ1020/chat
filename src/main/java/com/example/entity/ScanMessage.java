package com.example.entity;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 扫码消息
 */
public class ScanMessage {
    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MsgType;
    private String Event;
    private int EventKey;
    private String Ticket;

    public ScanMessage(String toUserName, String fromUserName, int createTime, String msgType, String event, int eventKey, String ticket) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Event = event;
        EventKey = eventKey;
        Ticket = ticket;
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

    public int getEventKey() {
        return EventKey;
    }

    public String getTicket() {
        return Ticket;
    }
}
