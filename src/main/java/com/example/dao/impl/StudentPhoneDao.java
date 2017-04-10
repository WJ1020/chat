package com.example.dao.impl;

import com.example.dao.entity.StudentPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String sql="INSERT INTO StdentPhone (sno,phone,state)";
        return this.jdbcTemplate.update(sql,studentPhone.getSno(),studentPhone.getPhone(),studentPhone.getState());
    }
    //查询所有的手机号信息
    public List<StudentPhone> findAll(){
        String sql="SELECT id,sno,phone,state FROM StudentPhone";
        RowMapper<StudentPhone> rowMapper=new BeanPropertyRowMapper<>(StudentPhone.class);
        List<StudentPhone> studentPhones=this.jdbcTemplate.query(sql,rowMapper);
        return studentPhones;
    }
    //根据学生的专业和年级查询（如果存在手机号则发送）
    public List<StudentPhone> findByMajor(String major,String grade){
        String sql="SELECT StudentPhone.id ,StudentPhone.sno,StudentPhone.phone,StudentPhone.state FROM StudentPhone,Student WHERE StudentPhone.sno=Student.sno AND Student.major=? AND Student.grade=?";
        RowMapper<StudentPhone> rowMapper=new BeanPropertyRowMapper<>(StudentPhone.class);
        List<StudentPhone> studentPhones=this.jdbcTemplate.query(sql,new Object[]{major,grade},rowMapper);
        return studentPhones;
    }

}
