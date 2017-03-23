package com.example.entity.media;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 语音媒体消息
 */
@JsonRootName("Voice")
public class Voice {
    @JacksonXmlProperty
    private String MediaId;
    public Voice(String MediaId){
        this.MediaId=MediaId;
    }
}
