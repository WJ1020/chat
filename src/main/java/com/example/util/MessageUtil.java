package com.example.util;

import com.example.entity.ImageRespMessage;
import com.example.entity.NewsRespMessage;
import com.example.entity.TextRespMessage;
import com.example.entity.media.Articles;
import com.thoughtworks.xstream.XStream;
import com.example.entity.media.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/5/20.
 * 图文消息发送
 */
public class MessageUtil {

    /**
     * 文本消息转换
     */
    public static String textMessageToXml(TextRespMessage textRespMessage){
        XStream xStream=new XStream();
        xStream.alias("xml",textRespMessage.getClass());
        return xStream.toXML(textRespMessage);
    }
    public static String imageMessageToXml(ImageRespMessage imageRespMessage){
        XStream xStream=new XStream();
        xStream.alias("xml",imageRespMessage.getClass());
        return xStream.toXML(imageRespMessage);
    }
    public static String newsMessageToXml(NewsRespMessage newsRespMessage){
        XStream xStream=new XStream();
        xStream.alias("Articles",ArrayList.class);
        xStream.alias("item",item.class);
        xStream.alias("xml",NewsRespMessage.class);
        return xStream.toXML(newsRespMessage);
    }

    public static void main(String args[]){
        item item=new item("欢迎关注-->title","欢迎关注-->description","http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/kibiaLzwGVAUnpsRFH8VqvFZNLBvCJc1Bib52ks0iaHZfPGFuibll9DJjmPU7G0Vicib9ibhW3kiacnXKFA6KNFicDlw40QQ\\/0?wx_fmt=jpeg","www.baidu.com");
        List<item> items=new ArrayList<>();
        items.add(item);
        NewsRespMessage newsRespMessage=new NewsRespMessage("11","22",33,"news",1,items);
        String result=newsMessageToXml(newsRespMessage);
        System.out.println(result);
    }
}
