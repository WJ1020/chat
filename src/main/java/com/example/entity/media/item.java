package com.example.entity.media;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 多条图文消息信息
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class item {
    @JacksonXmlProperty
    private String Title;
    @JacksonXmlProperty
    private String Description;
    @JacksonXmlProperty
    private String PicUrl;
    @JacksonXmlProperty
    private String Url;

    public item(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }
}
