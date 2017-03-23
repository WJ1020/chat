package com.example.entity.media;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 图文消息里的文章
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
@JsonRootName("Articles")
public class Articles {
    @JacksonXmlProperty
    private List<item> item;
    public Articles(List<com.example.entity.media.item> item) {
        this.item = item;
    }
}
