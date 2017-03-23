package com.example.util;

import com.example.dao.entity.SNSUserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/10.
 * json数据解析类
 */
public class JsonUtil {
    public static Map<String,String> jsonToMap(String str){
        ObjectMapper mapper=new ObjectMapper();
        Map<String,String> map=null;
        try {
            map=mapper.readValue(str,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static SNSUserInfo getUserInfo(String result)  {
        ObjectMapper mapper=new ObjectMapper();
        Map<String,Object> map=null;
        SNSUserInfo snsUserInfo=null;
        try {
            map=mapper.readValue(result,Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 因为此时没用到信用卡的(特权信息)一些信息，所以对这些并没有处理。
         */if (map.get("openid")!=null){
            snsUserInfo=new SNSUserInfo(map.get("openid").toString(),map.get("nickname").toString(),new Integer(map.get("sex").toString()),map.get("province").toString(),map.get("city").toString(),map.get("country").toString(),map.get("headimgurl").toString(),null,null);
            }else {
             System.out.println("获取信息出现了问题,微信服务器返回的信息为为:"+result);
        }
        return snsUserInfo;
    }

}

