package com.example.service;

import com.example.entity.WXJsConfig;
import com.example.util.OAuth;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by WangShiXiang on 2017/3/24.
 * 用来注入权限验证配置的一些数据
 */
@Service
public class JSApiService {
    /**
     * 根据url生成验证配置
     * @param url
     * @return
     */
    public WXJsConfig getWXJsConfig(String url){
        String nonce=create_nonceStr();
        String timestamp=create_timestamp();
        String jsapi_ticket=ConfigService.getJsapi_ticket();
        String siganture= OAuth.getSignature(nonce,jsapi_ticket,timestamp,url);
        WXJsConfig wxJsConfig=new WXJsConfig(true,ConfigService.getAppid(),timestamp,nonce,siganture);
        return wxJsConfig;
    }

    /**
     * 用来生成随机字符串
     * @return 返回随机字符串
     */
    public static String create_nonceStr(){
        UUID uuid=UUID.randomUUID();
        String nonce_str=uuid.toString();
        return nonce_str;
    }

    /**
     * 时间的格式化
     * @return
     */
    public static String create_timestamp(){
        long millis=System.currentTimeMillis();
        return Long.toString(millis/1000);
    }
}
