package com.example.entity.media;

/**
 * Created by WangShiXiang on 2017/3/12.
 * 微信素材文件
 */
public class WXMedia {
    private String type;
    private String media_id;
    private String created_at;

    public WXMedia(String type, String media_id, String created_at) {
        this.type = type;
        this.media_id = media_id;
        this.created_at = created_at;
    }
}
