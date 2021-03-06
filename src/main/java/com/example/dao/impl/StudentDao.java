package com.example.dao.impl;

import com.example.dao.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        String sql="select sno,name,sex,college,major,grade from Student WHERE major=?";
        RowMapper<Student> rowMapper=new BeanPropertyRowMapper<>(Student.class);
       return this.jdbcTemplate.query(sql,new Object[]{major},rowMapper);

    }
    public List<Student> findByMajorAndGrade(String major,String grade){
        String sql="select sno,name,sex,college,major,grade from Student WHERE major=? AND grade=?";
        RowMapper<Student> rowMapper=new BeanPropertyRowMapper<>(Student.class);
        return this.jdbcTemplate.query(sql,new Object[]{major,grade},rowMapper);

    }
    public List<Student> findAll(){
        String sql="select sno,name,sex,college,major,grade from Student";
        RowMapper<Student> rowMapper=new BeanPropertyRowMapper<>(Student.class);
        return   this.jdbcTemplate.query(sql,rowMapper);
    }
    public int delete(String sno){
        String sql="DELETE FROM student WHERE sno=?";
        return this.jdbcTemplate.update(sql,sno);
    }
    public int[] batchInsert(final List<Student> students){
        List<Object[]> batch=new ArrayList<>();
        for (Student student:students){
            Object[] values=new Object[]{
                    student.getSno(),
                    student.getName(),
                    student.getSex(),
                    student.getCollege(),
                    student.getMajor(),
                    student.getGrade()};
            batch.add(values);
            }
            return jdbcTemplate.batchUpdate(
                    "INSERT INTO student(sno, name, sex, college, major, grade) VALUES (?,?,?,?,?,?)",
                    batch
            );

    }
}
