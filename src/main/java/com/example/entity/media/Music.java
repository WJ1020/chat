package com.example.entity.media;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 音乐
 */
@JsonRootName("Music")
public class Music {
    @JacksonXmlProperty
    private String Tile;
    @JacksonXmlProperty
    private String Description;
    @JacksonXmlProperty
    private String MusicUrl;
    @JacksonXmlProperty
    private String HQMusicUrl;
    @JacksonXmlProperty
    private String ThumbMediaId;

    public Music(String tile, String description, String musicUrl, String HQMusicUrl, String thumbMediaId) {
        Tile = tile;
        Description = description;
        MusicUrl = musicUrl;
        this.HQMusicUrl = HQMusicUrl;
        ThumbMediaId = thumbMediaId;
    }
}
