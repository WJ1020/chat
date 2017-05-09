package com.example.view;

import com.example.service.ConfigService;
import com.example.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by WangShiXiang on 2017/3/10.
 * 菜单类
 */
public class Menu {
    @Autowired
    private ConfigService configService;
    public static String menu="{\n" +
            "    \"button\": [\n" +
            "        {\n" +
            "            \"name\": \"课堂管理\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"课堂点名\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/student\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"缺课名单\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/absences\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"随机点名\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/callname\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"我的课表\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/classtable\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"空闲教室\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/classroom\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"name\": \"我的大学\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"我要请假\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/leave\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"请假回执\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/leave_1\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"请假申请\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/leave_2\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"我的建议\", \n" +
            "                    \"url\": \"http://v.qq.com/\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"name\": \"教学教务\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"短信通知\", \n" +
            "                    \"url\": \"http://wx.wodeschool.cn/view/sendshortmessage\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"邮件通知\", \n" +
            "                    \"url\": \"http://wx.zealer.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"测试\", \n" +
            "                    \"key\": \"V1001\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static void main(String[] args){
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=WN_01rBT1PN6LJHzMr4dx_VYCc23iINE2RHabKFaXYbqengrXQ72FVx-CujocECYjudmqtwJ2b5Vqkm-wqbuHS4I3du3Ked-L6wXJIyjnbZcVHpFVd6hvvIYey__OJaHGCZcACAVRO";
       String str=HttpUtil.postDownloadJson(url,menu);
       System.out.println(str);
    }
}
