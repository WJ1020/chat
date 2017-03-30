package com.example.dao.impl;

import com.example.dao.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/17.
 * 学生表的数据库操作
 */
@Repository
public class StudentDao
{
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Student student){
        String sql="INSERT INTO student (sno ,name,sex,college,major,grade) VALUES (?,?,?,?,?,?)";
        return  this.jdbcTemplate.update(sql,student.getSno(),student.getName(),student.getSex(),student.getCollege(),student.getMajor(),student.getGrade());
    }
    public Student findBySno(String sno){
        Student student=null;
        String sql="select sno,name,sex,college,major,grade FROM Student WHERE sno=?";
       try {
           RowMapper<Student> rowMapper=new BeanPropertyRowMapper<>(Student.class);
           student=this.jdbcTemplate.queryForObject(sql,new Object[]{sno},rowMapper);
       }catch (EmptyResultDataAccessException e){
            System.out.println("查询的学号不存在");
       }
        return student;
    }
    public List<Student> findByMajor(String major){
        String sql="SELECT sno,name,";
        RowMapper<Student> rowMapper=new BeanPropertyRowMapper<>(Student.class);
        List<Student> students=this.jdbcTemplate.query(sql,new Object[]{major},rowMapper);
        return students;
    }
    public List<Student> findAll(){
        String sql="select sno,name,sex,college,major,grade from Student";
        RowMapper<Student> rowMapper=new BeanPropertyRowMapper<>(Student.class);
        List<Student> students=this.jdbcTemplate.query(sql,rowMapper);
        return students;
    }
    public int delete(String sno){
        String sql="DELETE FROM student WHERE sno=?";
        int count=this.jdbcTemplate.update(sql,sno);
        return count;
    }
}
