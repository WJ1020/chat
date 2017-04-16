package com.example.dao.impl;

import com.example.dao.entity.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/14.
 *
 */
@Repository
public class MentorDao {
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Mentor mentor){
        String sql="INSERT INTO Methor(openid,name,major,grade,phone )VALUES (?,?,?,?,?)";
        return this.jdbcTemplate.update(sql,mentor.getOpenid(),mentor.getName(),mentor.getMajor(),mentor.getGrade(),mentor.getPhone());
    }

    public List<Mentor> findByOpenId(String openid){
        String sql="SELECT id,openid,name,major,grade,phone FROM mentor WHERE openid=?";
        RowMapper<Mentor> rowMapper=new BeanPropertyRowMapper<>(Mentor.class);
        List<Mentor> mentors=this.jdbcTemplate.query(sql,new Object[]{openid},rowMapper);
        return mentors;
    }
    public Mentor findByMajorAndGrade(String major,String grade){
        String sql="SELECT id,openid,name,major,grade,phone FROM mentor WHERE major=? AND grade=?";
        Mentor mentor=null;
        try{
            RowMapper<Mentor> rowMapper=new BeanPropertyRowMapper<>(Mentor.class);
            mentor=this.jdbcTemplate.queryForObject(sql,new Object[]{major,grade},rowMapper);
        }catch (EmptyResultDataAccessException e){
            System.out.println("没有查询到"+grade+"级"+major+"专业的导员信息");
        }
        return mentor;
    }
    public int update(String openid,String name){
        String sql="UPDATE mentor SET openid=? WHERE name=?";
        return this.jdbcTemplate.update(sql,openid,name);
    }
    public List<Mentor> findByName(String name){
        String sql="SELECT id,openid,name,major,grade,phone FROM mentor WHERE name=?";
        RowMapper<Mentor> rowMapper=new BeanPropertyRowMapper<>(Mentor.class);
        return this.jdbcTemplate.query(sql,new Object[]{name},rowMapper);
    }


    
}
