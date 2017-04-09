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
            "                    \"url\": \"http://www.wodeschool.cn/view/student\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"缺课名单\", \n" +
            "                    \"url\": \"http://www.wodeschool.cn/view/absences\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"随机点名\", \n" +
            "                    \"url\": \"http://www.wodeschool.cn/view/callname\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"我的课表\", \n" +
            "                    \"url\": \"http://www.wodeschool.cn/view/classtable\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"空闲教室\", \n" +
            "                    \"url\": \"http://www.wodeschool.cn/view/classroom\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"name\": \"我的大学\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"我要请假\", \n" +
            "                    \"url\": \"http://www.soso.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"我要销假\", \n" +
            "                    \"url\": \"http://www.qq.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"申请资源\", \n" +
            "                    \"key\": \"V1001_GOOD\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"我的建议\", \n" +
            "                    \"url\": \"http://v.qq.com/\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"name\": \"校园生活\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"故障报修\", \n" +
            "                    \"url\": \"http://www.baidu.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"失物招领\", \n" +
            "                    \"url\": \"http://www.zealer.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"校园广播站\", \n" +
            "                    \"key\": \"V1001\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static void main(String[] args){
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=Eqz_XJ08kFU47pAyVHpMpR-SkPREEltvKUC2kR6rlCmjbyt2RmhIH6SADoVu15Xp4UJUrX3U4dP-2imvEqmkwN2dCQMlpt4qyscjStvNet1_aj6UsmDwy8MeGueJWCsgTKKaAEAARR";
       String str=HttpUtil.postDownloadJson(url,menu);
       System.out.println(str);
    }
}
