package com.example.dao.impl;

import com.example.dao.entity.StudentScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/4.
 *
 */
@Repository
public class StudentScoreDao {
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(StudentScore studentScore){
        String sql="INSERT INTO StudentScore(openid,major,courseName,sno,studentName,courseScore)VALUES (?,?,?,?,?,?)";
        return this.jdbcTemplate.update(sql,studentScore.getOpenid(),studentScore.getMajor(),studentScore.getCourseName(),studentScore.getSno(),studentScore.getStudentName(),studentScore.getCourseScore());
    }
    public List<StudentScore> findByOpenidAndMajor(String openid,String major){
        String sql="SELECT id,openid,major,courseName,sno,studentName,courseScore FROM StudentScore WHERE openid=?AND major=?";
        RowMapper<StudentScore> rowMapper=new BeanPropertyRowMapper<>(StudentScore.class);
        List<StudentScore> studentScores=this.jdbcTemplate.query(sql,new Object[]{openid,major},rowMapper);
        return studentScores;
    }

    public List<StudentScore> findByOpenidAndCourseName(String openid,String courseName,String major){
        String sql="SELECT id,openid,major,courseName,sno,studentName,courseScore FROM StudentScore WHERE openid=?AND courseName=?AND major=?";
        RowMapper<StudentScore> rowMapper=new BeanPropertyRowMapper<>(StudentScore.class);
        List<StudentScore> studentScores=this.jdbcTemplate.query(sql,new Object[]{openid,courseName,major},rowMapper);
        return studentScores;
    }

}
