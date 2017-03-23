package com.example.service;

import com.example.dao.entity.SNSUserInfo;
import com.example.service.DBService.SNSUserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;

/**
 * Created by WangShiXiang on 2017/3/17.
 * 用户信息的测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SNSUserInforService {
    @Autowired
    private SNSUserInfoService snsUserInfoService;
    @Test
    public void SNSUserInfoDaoTest(){
        SNSUserInfo snsUserInfo=new SNSUserInfo("oJ-Atw-BIhBu1XbwzJ0T0lduNzqk","2",1,"4","5","6","7",new LinkedList<String>(),"9");
        System.out.println(snsUserInfoService);
        snsUserInfoService.save(snsUserInfo);
        SNSUserInfo snsUserInfo1=snsUserInfoService.findByOpenId("1");
        System.out.println(snsUserInfo1.getOpenid());

    }
}
