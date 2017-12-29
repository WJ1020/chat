package com.example.entity.media;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/7.
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
public class Items {
    @JacksonXmlProperty
    private item item;
    public Items(item item){
        this.item=item;
    }



}
