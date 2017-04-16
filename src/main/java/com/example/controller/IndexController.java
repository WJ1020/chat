package com.example.controller;

import com.example.entity.TextRespMessage;
import com.example.service.MessageService;
import com.example.service.RespMessage;
import com.example.util.SignUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/8.
 * 主控制器
 */
@RestController
public class IndexController {
    /**
     * 用来对微信服务器接入参数的验证
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return 请求成功原样返回echostr
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String weChatVerification(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,@RequestParam("nonce")String nonce,@RequestParam("echostr")String echostr){
       if(SignUtil.checkSignature(signature,timestamp,nonce)){
           return echostr;
       }
        return "";
    }
    @Resource
    private MessageService messageService;
    /*
    微信推送的所有消息在这接收
     */
    @RequestMapping(value = "/",method = RequestMethod.POST,produces = "application/xml;charset=UTF-8")
    public Object WXMessage(@RequestBody Map<String,String> map){
        Object object=messageService.typeMessage(map);
        return object;
    }


}
