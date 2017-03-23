package com.example.entity;

import com.example.entity.media.Articles;
import com.example.entity.media.item;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 图文消息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("xml")
public class NewsRespMessage {
    @JacksonXmlProperty
    private String ToUserName;
    @JacksonXmlProperty
    private String FromUserName;
    @JacksonXmlProperty
    private int CreateTime;
    @JacksonXmlProperty
    private String MsgType;
    @JacksonXmlProperty
    private int ArticleCont;

    @JacksonXmlProperty
    private Articles Articles;

    public NewsRespMessage(String toUserName, String fromUserName, int createTime, String msgType, int articleCont, com.example.entity.media.Articles articles) {
        ToUserName = toUserName;
        FromUserName = fromUserName;
        CreateTime = createTime;
        MsgType = msgType;
        ArticleCont = articleCont;
        Articles = articles;
    }
}
