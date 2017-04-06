package com.example.controller;

import com.example.dao.entity.SNSUserInfo;
import com.example.dao.entity.Student;
import com.example.dao.entity.Teacher;
import com.example.entity.CLICKMessage;
import com.example.entity.WXJsConfig;
import com.example.service.ConfigService;
import com.example.service.DBService.TeacherService;
import com.example.service.JSApiService;
import com.example.service.RespEventService;
import com.example.service.DBService.SNSUserInfoService;
import com.example.util.OAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    @Autowired
    private RespEventService respEventService;
    @Autowired
    private JSApiService jsApiService;
    @Autowired
    private TeacherService teacherService;
    @RequestMapping(value = "student",method = RequestMethod.GET)
    public String student(@RequestParam(value = "code",required = false) String code, HttpServletResponse httpServletResponse){
        if (code!=null){
            //首先获取
            Map<String,String> map= OAuth.getAccessToken(ConfigService.getAppid(),ConfigService.getSecret(),code);
            if(map!=null){
                String userAccess_token=map.get("access_token");
                String openID=map.get("openid");
                Cookie cookie=new Cookie("openid",openID);
                httpServletResponse.addCookie(cookie);
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



    @GetMapping("/test")
    public Object test(){
        return "test";
    }
    @RequestMapping(value = "/js",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WXJsConfig getWXJsConfig(@RequestParam("u") String url){
        return jsApiService.getWXJsConfig(url);
    }
    @RequestMapping(value = "/findteacher")
    @ResponseBody
    public List<Teacher> findTeacherByName(@RequestParam("name") String name){
        List<Teacher> teacher=teacherService.findByName(name);
        return teacher;
    }
    //跳转到绑定页面
    @RequestMapping(value = "/bindopenid",method = RequestMethod.GET)
    public String bindIOpenid(){
        return "bindopenid";
    }
    @RequestMapping(value="/absences",method = RequestMethod.GET)
    public String absences(){
        return "absences";
    }

    //跳转缺课
    @RequestMapping(value = "/absencesstudent",method = RequestMethod.GET)
    public String absences_student(){
        return "absencesstudent";
    }
    @RequestMapping(value = "/callname",method = RequestMethod.GET)
    public String returnCallName(){
        return "callname";
    }
    @RequestMapping(value = "/callnamescore",method = RequestMethod.GET)
    public String returnCallNameScore(){
        return "callnamescore";
    }
}
