package com.example.entity;

/**
 * Created by WangShiXiang on 2017/3/22.
 * 微信js授权信息
 */
public class WXJsConfig {
    private boolean debug;//是否开启调试
    private String appId;
    private String timestamp;//时间戳
    private String nonceStr;//生成签名的随机串
    private String signature;//签名

    public WXJsConfig(boolean debug, String appId, String timestamp, String nonceStr, String signature) {
        this.debug = debug;
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonceStr = nonceStr;
        this.signature = signature;
    }

}