package com.example.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 视频消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class VideoReqMessage {
    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MsgType;
    private String MediaId;
    private String ThumbMediaId;
    private long MsgId;
    public VideoReqMessage(){

    }

    public VideoReqMessage(String toUserName, String fromUserName, int createTime, String msgType, String mediaId, String thumbMediaId, long msgId) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        MediaId = mediaId;
        ThumbMediaId = thumbMediaId;
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

    public String getMediaId() {
        return MediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public long getMsgId() {
        return MsgId;
    }
}
