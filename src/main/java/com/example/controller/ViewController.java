package com.example.controller;

import com.example.dao.entity.SNSUserInfo;
import com.example.entity.CLICKMessage;
import com.example.service.ConfigService;
import com.example.service.RespEventService;
import com.example.service.DBService.SNSUserInfoService;
import com.example.util.OAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 微信网页的js
 */
@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private SNSUserInfoService snsUserInfoService;
    @RequestMapping(value = "student",method = RequestMethod.GET)
    public String student(@RequestParam(value = "code",required = false) String code){
        if (code!=null){
            //首先获取
            Map<String,String> map= OAuth.getAccessToken(ConfigService.getAppid(),ConfigService.getSecret(),code);
            if(map!=null){
                String userAccess_token=map.get("access_token");
                String openID=map.get("openid");
                SNSUserInfo snsUserInfo=OAuth.getUserInfo(userAccess_token,openID);
                if (snsUserInfo!=null){
                    snsUserInfoService.save(snsUserInfo);
                }else {
                    System.out.println("获取不到用户的详细信息");
                }
            }
        }
        return "student";
    }

    @Autowired
    private RespEventService respEventService;
    @ResponseBody
    @GetMapping("/test")
    public Object test(){
      return   respEventService.event(new CLICKMessage("123","321",123,"event","CLICK","12378"));
    }

//    @GetMapping("wxlogin")
//    public String wxLogin() throws UnsupportedEncodingException {
//        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
//        String access_token= ConfigService.getAccess_token();
//        String appid="wx8d03b75a183070f8";
//        String response_type="http://www.wodeschool.cn/view/student";
//        response_type= URLEncoder.encode(response_type,"utf-8");
//        url=url.replace("APPID",appid).replace("REDIRECT_URI",response_type).replace("SCOPE","snsapi_userinfo");
//        return "redirect:"+url;
//    }
}
