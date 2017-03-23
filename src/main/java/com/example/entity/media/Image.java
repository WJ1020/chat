package com.example.entity.media;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/8.
 * 资源
 */
@JsonRootName("Image")
public class Image {
    @JacksonXmlProperty
    private String MediaId;
    public Image(String MedialID){
        this.MediaId=MedialID;
    }
}
