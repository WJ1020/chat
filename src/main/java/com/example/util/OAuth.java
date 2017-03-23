package com.example.util;

import com.example.dao.entity.SNSUserInfo;
import com.example.service.ConfigService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/14.
 * 用户授权，以及获取用户的信息
 */
public class OAuth {
    /**
     * 用第一步获取的code来换取access_token,,同时会返回openid refresh_token 首先请注意，
     * 这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同。
     * @param appid 你的公众号设置的appid
     * @param secret 公众号的唯一密钥
     * @param code 微信服务器请求带的code
     * @return 返回
     */
    public static Map<String, String> getAccessToken(String appid,String secret,String code){
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        String grant_type="authorization_code";
        url=url.replace("APPID",appid).replace("SECRET",secret).replace("CODE",code);
        String result=HttpUtil.getDataByInternet(url);
        Map<String,String> map=JsonUtil.jsonToMap(result);
        if (map.get("access_token")!=null){

            return map;
        }else {
            System.out.println("没用成功获取到access_token，此时的code为："+code);
        }
        return null;
    }

    /**
     * 如果有必要时可以刷新第一步获取的access_token
     * @param refresh_token
     * @return
     */
    public static String reFresh(String appid,String refresh_token){
        String url="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        url=url.replace("APPID",appid).replace("REFRESH_TOKEN",refresh_token);
        String result=HttpUtil.getDataByInternet(url);
        Map<String,String> map=JsonUtil.jsonToMap(result);
        if(map.get("access_token")!=null){
            return map.get("refresh_token");
        }
        return null;
    }

    /**
     * 用来获取用户的详细信息，
     * @param access_token  注意此处不是全局配置的那个access_token
     * @param openid 公众号的唯一标识
     * @return
     */
    public static SNSUserInfo getUserInfo(String access_token,String openid){
        String url="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url=url.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid);
        String result=HttpUtil.getDataByInternet(url);
        SNSUserInfo snsUserInfo=JsonUtil.getUserInfo(result);
//        System.out.println(snsUserInfo);
        return snsUserInfo;
    }

    /**
     * 用来获取jsapi_ticket
     * @param access_token
     * @return
     */
    public static String getJsApiTicket(String access_token){
        String JsApiTicket=null;
        String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        url=url.replace("ACCESS_TOKEN",access_token);
        String result=HttpUtil.getDataByInternet(url);
        Map<String,String> map=JsonUtil.jsonToMap(result);
        if (map.get("errmsg").equals("ok")){
            JsApiTicket=map.get("ticket");
        }
        return JsApiTicket;
    }

    /**
     * JS-SDK权限验证的签名
     * @param noncestr 随机字符串
     * @param jsapi_ticket 有效时间内的jsapi_tivken
     * @param timestamp 时间戳
     * @param url 当前网页的URL，不包含#及其后面部分
     * @return
     */
    public static String getSignature(String noncestr,String jsapi_ticket,String timestamp,String url){
        String result=null;
        StringBuffer sb=new StringBuffer();
        sb.append("jsapi_ticket=").append(jsapi_ticket).append("&noncestr=").append(noncestr).append("&timestamp=")
                .append(timestamp).append("&url=").append(url);
        String string1=sb.toString();
        StringBuffer stringBuffer=new StringBuffer();
        //sha-1加密开始
        try {
            MessageDigest md=MessageDigest.getInstance("SHA");
            md.update(string1.getBytes());
            byte[] buf=md.digest();
            for (byte b:buf){
                stringBuffer.append(String.format("%02X", b));
            }
            result=stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
