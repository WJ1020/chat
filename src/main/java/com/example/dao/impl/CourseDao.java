package com.example.dao.impl;

import com.example.dao.entity.Course;
import com.example.dao.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/26.
 * 课程表的增删该查
 */
@Repository
public class CourseDao {
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Course course){
        String sql="INSERT INTO Course (openid,name,college,major,grade,teacherName,count) VALUES (?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(sql,course.getOpenid(),course.getName(),course.getCollege(),course.getMajor(),course.getGrade(),course.getTeacherName(),course.getCount());
    }

    public Course findById(int id){
        Course course=null;
        try{
            String sql="SELECT id,openid,name,college,major,grade,teacherName,count FROM Course WHERE id=?";
            RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
            course=this.jdbcTemplate.queryForObject(sql,new Object[]{id},rowMapper);
        }catch (EmptyResultDataAccessException e){
            System.out.println("id为："+id+" 的课程表不存在");
        }
        return course;
    }
    public List<Course> findByOpenid(String openid){
        String sql="SELECT id,openid,name,college,major,grade,teacherName,count FROM Course WHERE openid=?";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        List<Course> courses=this.jdbcTemplate.query(sql,new Object[]{openid},rowMapper);
        return courses;
    }

    /**
     * 根据openid和当前的节次来确定此时的课
     * @param openid
     * @param i
     * @return
     */
    public List<Course> findNowCourse(String openid,int i){
        String sql="";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        List<Course> courses=this.jdbcTemplate.query(sql,new Object[]{openid,i},rowMapper);
        return courses;
    }

    public int[] batchUpdateOpenid(List<Course> courses){
        String sql="UPDATE Course SET openid=? WHERE id=?";
        int[] updateCounts=jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Course course=courses.get(i);
                preparedStatement.setString(1,course.getOpenid());
                preparedStatement.setInt(2,course.getId());
            }

            @Override
            public int getBatchSize() {
                return courses.size();
            }
        });
        return updateCounts;
    }
}
