package com.example.entity;

import com.example.entity.media.Video;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 回复视频消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class VideoRespMessage {
    @JacksonXmlProperty
    private String ToUserName;
    @JacksonXmlProperty
    private String FromUserName;
    @JacksonXmlProperty
    private int CreateTime;
    @JacksonXmlProperty
    private String MsgType;
    @JacksonXmlProperty
    private Video Video;

    public VideoRespMessage(String toUserName, String fromUserName, int createTime, String msgType, com.example.entity.media.Video video) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Video = video;
    }
}
