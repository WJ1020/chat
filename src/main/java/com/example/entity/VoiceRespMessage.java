package com.example.entity;

import com.example.entity.media.Voice;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 语音回复消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class VoiceRespMessage {
    @JacksonXmlProperty
    private String ToUserName;
    @JacksonXmlProperty
    private String FromUserName;
    @JacksonXmlProperty
    private int CreateTime;
    @JacksonXmlProperty
    private String MsgType;
    @JacksonXmlProperty
    private Voice Voice;
    public VoiceRespMessage(){

    }

    public VoiceRespMessage(String toUserName, String fromUserName, int createTime, String msgType, Voice Voice) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        this.Voice = Voice;
    }

}
