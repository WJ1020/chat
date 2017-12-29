package com.example.service;

import com.example.entity.*;
import com.example.entity.media.Image;
import com.example.entity.media.Video;
import com.example.entity.media.Voice;
import com.example.entity.media.item;
import com.example.util.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 对接受的消息进行处理
 */
@Service
public class RespMessage {
    /**
     * 接收文本消息
     * @param message
     * @return
     */
    public Object message(TextReqMessage message){
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),message.getMsgType(),message.getContent());
        return textRespMessage;
    }
    public Object message(ImageReqMessage message){
        ImageRespMessage imageRespMessage=new ImageRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),message.getMsgType(),new Image(message.getMediaId()));
        return imageRespMessage;
    }
    public Object message(VoiceReqMessage message){
        VoiceRespMessage voiceRespMessage=new VoiceRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),message.getMagType(),new Voice(message.getMediaId()));
        return voiceRespMessage;
    }
    public Object message(VideoReqMessage message){
//        Video video=new Video(message.getMediaId(),"这是标题","这是视频描述");
//        System.out.println(message.getMediaId());
//        System.out.println(message.getThumbMediaId());
//        VideoRespMessage videoRespMessage=new VideoRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),message.getMsgType(),video);
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","你发送了视频");
        return textRespMessage;
    }
    public Object message(ShortVideoReqMessage message){
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","你发送了小视频");
        return textRespMessage;
    }
    public Object message(LocationReqMessage message){
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","你发送了位置"+message.getLabel());
        return textRespMessage;
    }
    public Object message(LinkReqMessage message){
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","你发送了链接");
        return textRespMessage;
    }

}
