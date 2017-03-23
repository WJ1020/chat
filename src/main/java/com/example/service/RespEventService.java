package com.example.service;

import com.example.entity.*;
import com.example.entity.media.Articles;
import com.example.entity.media.item;
import org.springframework.stereotype.Service;

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
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","你点击了测试按钮");
      String url="http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/kibiaLzwGVAUlFW8ic445zp9IQibuCF1ntn4W2KvE6XXcg41z1wicb7sibz2hzoHicrpMUpCTF3jlkhvPd3BzZVIrEI7A\\/0";
        item item=new item("测试消息","这是测试图文消息和自定义事件",url,"https://www.baidu.com");
        List<item> items=new LinkedList<>();
        items.add(item);
        Articles articles=new Articles(items);
        NewsRespMessage newsRespMessage=new NewsRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"news",1,articles);
        return newsRespMessage;
    }
    //扫码事件
    public Object event(ScanMessage message){
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","你扫描了二维码："+message.getEventKey());
        return textRespMessage;
    }
    //关注事件
    public Object event(FollowMessage message){
        TextRespMessage textRespMessage=new TextRespMessage(message.getFromUserName(),message.getToUserName(),message.getCreateTime(),"text","欢迎你的关注");
        return textRespMessage;
    }
    public Object event(LocationEventMessage message){
        return null;
    }
}
