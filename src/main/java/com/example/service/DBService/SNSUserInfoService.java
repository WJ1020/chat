package com.example.service.DBService;

import com.example.dao.entity.SNSUserInfo;
import com.example.dao.impl.SNSUserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WangShiXiang on 2017/3/16.
 * 用户信息处理
 */
@Service
public class SNSUserInfoService {
    @Autowired
    private SNSUserInfoDao snsUserInfoDao;

    public void save(SNSUserInfo snsUserInfo){
        if(findByOpenId(snsUserInfo.getOpenid())==null){
            snsUserInfoDao.save(snsUserInfo);
        }
    }

    public SNSUserInfo findByOpenId(String openid){
        return this.snsUserInfoDao.findByOpenId(openid);
    }

}
