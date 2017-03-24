package com.example.service;

import com.example.util.HttpUtil;
import com.example.util.JsonUtil;
import com.example.util.OAuth;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/9.
 * accession_token等配置信息
 */
@Service
public class ConfigService implements Runnable{

    private static String access_token=null;
    private static String grant_type="client_credential";
    private static String appid="wx8d03b75a183070f8";
    private static String secret="855be83fe6c8947c61e3c48d3bd12f19";
    private static String jsapi_ticket=null;
    @PostConstruct
    public void initMethod(){
        ConfigService configService=new ConfigService();
       Thread thread=new Thread(configService);
       thread.start();
    }

    public static String getAccess_token() {
        return access_token;
    }

    public static String getAppid() {
        return appid;
    }

    public static String getSecret() {
        return secret;
    }

    public static String getJsapi_ticket(){
        return jsapi_ticket;
    }

    @Override
    public void run() {
        while (true){
            try {
                String url="https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret;
                String result= HttpUtil.getDataByInternet(url);
                Map map= JsonUtil.jsonToMap(result);
                access_token=(String) map.get("access_token");
                System.out.println("获取到的access_token为: "+access_token);
                jsapi_ticket= OAuth.getJsApiTicket(access_token);
                System.out.println("获取到的jsapi_ticket为："+jsapi_ticket);
                Thread.sleep(1000*60*120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
