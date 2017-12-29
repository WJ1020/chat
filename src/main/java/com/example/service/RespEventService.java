package com.example.service;

import com.example.entity.*;
import com.example.entity.media.Articles;
import com.example.entity.media.item;
import com.example.util.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 对事件的处理,并返回处理结果
 */
@Service
public class RespEventService {
    //自定义菜单事件
    public Object event(CLICKMessage message){

        
        return null;
    }
    //扫码事件
    public Object event(ScanMessage message){
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","你扫描了二维码："+message.getEventKey());
        return textRespMessage;
    }
    //关注事件
    public Object event(FollowMessage message){
        item item=new item("欢迎你的关注","欢迎你的关注，我会随时为你服务......","http://upload-images.jianshu.io/upload_images/4047649-7691b3bf1d55d6b8.jpg?imageMogr2/auto-orient/strip","http://www.jianshu.com/p/7af6131974ac");
        List<item> items=new ArrayList<>();
        items.add(item);
        NewsRespMessage newsRespMessage=new NewsRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"news",1,items);
        return MessageUtil.newsMessageToXml(newsRespMessage);
    }
    public Object event(LocationEventMessage message){
        return null;
    }
}
