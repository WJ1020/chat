package com.example.dao.impl;

import com.example.dao.entity.StudentPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by WangShiXiang on 2017/4/9.
 * 手机号数据库的操作
 */
@Repository
public class StudentPhoneDao {
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(StudentPhone studentPhone){
        String sql="";
        return this.jdbcTemplate.update(sql,studentPhone.getSno(),studentPhone.getPhone(),studentPhone.getState());
    }
}
