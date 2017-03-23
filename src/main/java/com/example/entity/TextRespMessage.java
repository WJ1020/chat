package com.example.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


/**
 * Created by WangShiXiang on 2017/3/8.
 * 对微信普通消息回复
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class TextRespMessage {
    @JacksonXmlProperty
    @JacksonXmlCData
    private String ToUserName;
    @JacksonXmlProperty
    @JacksonXmlCData
    private String FromUserName;
    @JacksonXmlProperty
    @JacksonXmlCData
    private int CreateTime;
    @JacksonXmlProperty
    @JacksonXmlCData
    private String MsgType;
    @JacksonXmlProperty
    @JacksonXmlCData
    private String Content;
    public TextRespMessage(){

    }

    public TextRespMessage(String toUserName, String fromUserName, int createTime, String msgType, String content) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        Content = content;
    }
}
