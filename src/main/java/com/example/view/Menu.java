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
@Component
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
            "                    \"name\": \"随机提问\", \n" +
            "                    \"url\": \"http://v.qq.com/\"\n" +
            "                }, \n" +
            "                {\n" +
            "                    \"type\": \"click\", \n" +
            "                    \"name\": \"我的课程\", \n" +
            "                    \"key\": \"V1001_GOOD\"\n" +
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
//    @PostConstruct
    public void init(){
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=f5Wqi6s9jO6z1LkBL5Zjjld3zZvDGE3bF1pGtppPUIAXTLIzObJcX3qaKc6CTgxJ4aZ0Yk2h5YoiH5CW6rHCRvpsYdp_9GKJ_FnD3ZS2lMKm4CAz7EKZMTIr0VBjWz9iTUAfADAABH";
       String str=HttpUtil.postDownloadJson(url,menu);
       System.out.println(str);
    }
}
