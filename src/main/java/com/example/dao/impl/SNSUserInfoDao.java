package com.example.dao.impl;

import com.example.dao.entity.SNSUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WangShiXiang on 2017/3/16.
 * 用户信息的持久化类
 */
@Repository
public class SNSUserInfoDao {

    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 用来储存微信用户的详情信息
     * @param snsUserInfo
     */
    public void save(SNSUserInfo snsUserInfo){
        String sql="INSERT INTO SNSUserInfo (openid,nickname,sex,province,city,country,headimgurl,privilege,unionid) VALUES (?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql,snsUserInfo.getOpenid(),snsUserInfo.getNickname(),snsUserInfo.getSex(),snsUserInfo.getProvince(),snsUserInfo.getCity(),snsUserInfo.getCountry(),snsUserInfo.getHeadimgurl(),null,snsUserInfo.getUnionid());
    }

    /**
     * 根据用户的openid查询
     * @param openid
     */
    public SNSUserInfo findByOpenId(String openid){
        SNSUserInfo snsUserInfo=null;
        try {
            String sql="SELECT  openid,nickname,sex,province,city,country,headimgurl,privilege,unionid FROM SNSUserInfo WHERE openid=?";
            snsUserInfo= this.jdbcTemplate.queryForObject(sql,new Object[]{openid},new SNSUserInfoDaoMapper());
        }catch (EmptyResultDataAccessException e){

        }
        return snsUserInfo;
    }

    private static final class SNSUserInfoDaoMapper implements RowMapper<SNSUserInfo>{

        @Override
        public SNSUserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            SNSUserInfo snsUserInfo=new SNSUserInfo();
            snsUserInfo.setOpenid(resultSet.getString("openid"));
            snsUserInfo.setNickname(resultSet.getString("nickname"));
            snsUserInfo.setSex(resultSet.getInt("sex"));
            snsUserInfo.setProvince(resultSet.getString("province"));
            snsUserInfo.setCity(resultSet.getString("city"));
            snsUserInfo.setCountry(resultSet.getString("country"));
            snsUserInfo.setHeadimgurl(resultSet.getString("headimgurl"));
//            snsUserInfo.setPrivilege(resultSet.getString("privilege"));
            snsUserInfo.setUnionid(resultSet.getString("unionid"));
            return snsUserInfo;
        }
    }
}
