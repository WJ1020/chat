package com.example.entity;

import com.example.entity.media.Voice;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 接收的用户的语音消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class VoiceReqMessage {
    private String ToUserName;
    private String FromUserName;
    private int CreateTime;
    private String MagType;
    private String PicUrl;
    private String MediaId;
    private String Format;
    private long MsgId;

    public VoiceReqMessage(String toUserName, String fromUserName, int createTime, String magType, String picUrl, String mediaId, String format, long msgId) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MagType = magType;
        PicUrl = picUrl;
        MediaId = mediaId;
        Format = format;
        MsgId = msgId;
    }

    public VoiceReqMessage(){

    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(int createTime) {
        CreateTime = createTime;
    }

    public String getMagType() {
        return MagType;
    }

    public void setMagType(String magType) {
        MagType = magType;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
