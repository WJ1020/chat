package com.example.service;

import com.example.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/9.
 * 用来对微信的消息进行分类
 */
@Service
public class MessageService {
    @Resource
    private RespMessage respMessage;
    @Resource
    private EventService eventService;

    public Object typeMessage(Map<String,String> map){
        if(map.get("MsgType").equalsIgnoreCase("text")){
            TextReqMessage textReqMessage= new TextReqMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("Content"),new Long(map.get("MsgId")));
            return respMessage.message(textReqMessage);
        }else if(map.get("MsgType").equalsIgnoreCase("image")){
            ImageReqMessage imageReqMessage= new ImageReqMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("PicUrl"),map.get("MediaId"),new Long(map.get("MsgId")));
            return respMessage.message(imageReqMessage);
        }else if(map.get("MsgType").equalsIgnoreCase("voice")){
            VoiceReqMessage voiceReqMessage= new VoiceReqMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("PicUrl"),map.get("MediaId"),map.get("Format"),new Long(map.get("MsgId")));
            return respMessage.message(voiceReqMessage);
        }else if (map.get("MsgType").equalsIgnoreCase("video")){
            VideoReqMessage videoReqMessage=new VideoReqMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("MediaId"),map.get("ThumbMediaId"),new Long(map.get("MsgId")));
            return respMessage.message(videoReqMessage);
        }else  if (map.get("MsgType").equalsIgnoreCase("shortvideo")){
            ShortVideoReqMessage shortVideoReqMessage=new ShortVideoReqMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("MediaId"),map.get("ThumbMediaId"),new Long(map.get("MsgId")));
            return respMessage.message(shortVideoReqMessage);
        }else if(map.get("MsgType").equalsIgnoreCase("location")){
            LocationReqMessage locationReqMessage=new LocationReqMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),new Double(map.get("Location_X")),new Double(map.get("Location_Y")),new Integer(map.get("Scale")),map.get("Label"),new Long(map.get("MsgId")));
            return respMessage.message(locationReqMessage);
        }else if(map.get("MsgType").equalsIgnoreCase("link")){
            LinkReqMessage linkReqMessage=new LinkReqMessage(map.get("ToUserName"),map.get("FromUserName"),new Integer(map.get("CreateTime")),map.get("MsgType"),map.get("Title"),map.get("Description"),map.get("Url"),new Long(map.get("MsgId")));
            return respMessage.message(linkReqMessage);
        }else if (map.get("MsgType").equalsIgnoreCase("event")){
           return eventService.event(map);
        }
        return null;
    }
}
