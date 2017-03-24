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

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}