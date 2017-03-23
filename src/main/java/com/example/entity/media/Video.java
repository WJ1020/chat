package com.example.entity.media;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 视频
 */
@JsonRootName("Video")
public class Video {
    @JacksonXmlProperty
    private String MediaId;
    @JacksonXmlProperty
    private String Title;
    @JacksonXmlProperty
    private String Description;

    public Video(String mediaId, String title, String description) {
        MediaId = mediaId;
        Title = title;
        Description = description;
    }
}
