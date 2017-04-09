package com.example.dao.impl;

import com.example.dao.entity.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/7.
 * 教室表
 */
@Repository
public class ClassRoomDao {
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(ClassRoom classRoom){
        String sql="INSERT INTO ClassRoom (number,type,seating,state) VALUES (?,?,?,?)";
        return this.jdbcTemplate.update(sql,classRoom.getNumber(),classRoom.getType(),classRoom.getSeating(),classRoom.getState());
    }

    public List<ClassRoom> findAll(){
        String sql="SELECT id,number,type,seating,state FROM ClassRoom ORDER BY number";
        RowMapper<ClassRoom> roomRowMapper=new BeanPropertyRowMapper<>(ClassRoom.class);
        List<ClassRoom> classRooms=this.jdbcTemplate.query(sql,roomRowMapper);
        return classRooms;
    }

    public ClassRoom findByNumber(String number){
        String sql="SELECT id,number,type,seating,state FROM ClassRoom WHERE number=?";
        ClassRoom classRoom=null;
        try {
            RowMapper<ClassRoom> rowMapper=new BeanPropertyRowMapper<>(ClassRoom.class);
            classRoom=this.jdbcTemplate.queryForObject(sql,new Object[]{number},rowMapper);
        }catch (EmptyResultDataAccessException e){
            System.out.println("没有查询到编号(number)为:"+number+" 的教室");
        }
        return classRoom;
    }

}
