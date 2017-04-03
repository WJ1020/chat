package com.example.dao.impl;

import com.example.dao.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/26.
 * 教师表
 */
@Repository
public class TeacherDao {

    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;;

    public int save(Teacher teacher){
        String sql="INSERT INTO Teacher(openid,name,college)VALUES (?,?,?)";
        return this.jdbcTemplate.update(sql,teacher.getOpenid(),teacher.getName(),teacher.getCollege());
    }
    public Teacher findById(int id){
        Teacher teacher=null;
        String sql="SELECT id,openid,name,college FROM Teacher WHERE id=?";
        try {
            RowMapper<Teacher> rowMapper=new BeanPropertyRowMapper<>(Teacher.class);
            teacher=this.jdbcTemplate.queryForObject(sql,new Object[]{id},rowMapper);
        }catch (EmptyResultDataAccessException e){
            System.out.println("id为 "+id+" 的教师不存在");
        }
        return teacher;
    }
    public Teacher findByOpenid(String openid){
        Teacher teacher=null;
        String sql="SELECT id,openid,name,college FROM Teacher WHERE openid=?";
        try {
            RowMapper<Teacher> rowMapper=new BeanPropertyRowMapper<>(Teacher.class);
            teacher=this.jdbcTemplate.queryForObject(sql,new Object[]{openid},rowMapper);
        }catch (EmptyResultDataAccessException e){
            System.out.println("openid为 "+openid+" 的教师不存在");
        }
        return teacher;
    }
    public List<Teacher> findByName(String name){
        String sql="SELECT id,openid,name,college FROM Teacher WHERE name=?";
        RowMapper<Teacher> rowMapper=new BeanPropertyRowMapper<>(Teacher.class);
        List<Teacher> teachers=this.jdbcTemplate.query(sql,new Object[]{name},rowMapper);
        return teachers;
    }
    public int setOpenid(int id,String openid){
        String sql="UPDATE Teacher SET openid=? WHERE id=?";
        return this.jdbcTemplate.update(sql,openid,id);
    }

}
