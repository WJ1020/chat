package com.example.entity;

import com.example.entity.media.Music;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 音乐消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class MusicRespMessage {
    @JacksonXmlProperty
    private String ToUserName;
    @JacksonXmlProperty
    private String FromUserName;
    @JacksonXmlProperty
    private int CreateTime;
    @JacksonXmlProperty
    private String MsgType;
    @JacksonXmlProperty
    private Music Music;

    public MusicRespMessage(String toUserName, String fromUserName, int createTime, String msgType, com.example.entity.media.Music music) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Music = music;
    }
}
