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
            "            \"name\": \"我的课堂\", \n" +
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
            "                }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"name\": \"校园生活\", \n" +
            "            \"sub_button\": [\n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"故障报修\", \n" +
            "                    \"url\": \"http://www.soso.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"view\", \n" +
            "                    \"name\": \"申请资源\", \n" +
            "                    \"url\": \"http://v.qq.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"测试\", \n" +
            "                    \"key\": \"V1001_GOOD\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    public static void main(String[] args){
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=fZbsQfkJx2oQENmjQF7px5H0CnIX3P2VwnNV12Z79zLgTwGZTJfuwjWTgt-ecLG0k8MAoe_Xb2BU2A58oI4CdzxOpZ9hhGcH1YluYToRJ46t9XkucgeSKROkkXrz-pZ2IZFdAEAULR";
       String str=HttpUtil.postDownloadJson(url,menu);
       System.out.println(str);
    }
}
