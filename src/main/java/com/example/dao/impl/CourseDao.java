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
import java.util.ArrayList;
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
        String sql="INSERT INTO Course (openid,name,college,major,grade,teacherName,count,locale) VALUES (?,?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(sql,course.getOpenid(),course.getName(),course.getCollege(),course.getMajor(),course.getGrade(),course.getTeacherName(),course.getCount(),course.getLocale());
    }

    public Course findById(int id){
        Course course=null;
        try{
            String sql="SELECT id,openid,name,college,major,grade,teacherName,count,locale FROM Course WHERE id=?";
            RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
            course=this.jdbcTemplate.queryForObject(sql,new Object[]{id},rowMapper);
        }catch (EmptyResultDataAccessException e){
            System.out.println("id为："+id+" 的课程表不存在");
        }
        return course;
    }
    public List<Course> findByOpenid(String openid){
        String sql="SELECT id,openid,name,college,major,grade,teacherName,count,locale FROM Course WHERE openid=?";
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
        String sql="SELECT id,openid,name,college,major,grade,teacherName,count,locale FROM Course WHERE openid=? AND count=?";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        List<Course> courses=this.jdbcTemplate.query(sql,new Object[]{openid,i},rowMapper);
        return courses;
    }
    //根据教师和学院查询
    public List<Course> findNameCollege(String teacherName,String college){
        String sql="SELECT id,openid,name,college,major,grade,teacherName,count,locale FROM Course WHERE teacherName=? AND college=?";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        List<Course> courses=this.jdbcTemplate.query(sql,new Object[]{teacherName,college},rowMapper);
        return courses;
    }
    //用来查询教师的所有课程信息(筛选出重复信息)
    public List<Course> findCourseByOpenid(String openid){
        String sql=" SELECT a.id,a.openid,a.name,a.college,a.major,a.grade,a.teacherName,a.count,a.locale FROM Course a WHERE a.id=(SELECT max(id) FROM Course WHERE a.name=name)  and a.openid=?";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        return this.jdbcTemplate.query(sql,new Object[]{openid},rowMapper);
    }
    public List<Course> findAllCourse(){
        String sql="SELECT a.id,a.openid,a.name,a.college,a.major,a.grade,a.teacherName,a.count,a.locale FROM Course a WHERE a.id=(SELECT max(id) FROM Course WHERE a.name=name)";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        return this.jdbcTemplate.query(sql,rowMapper);
    }
    /**
     * 根据课的任课老师和学院更新openid
     * @param openid
     * @param college
     * @param teacherName
     * @return
     */
    public int updateCourse(String openid,String college,String teacherName){
        String sql="UPDATE Course SET openid=? WHERE college=? AND teacherName=?";
        return this.jdbcTemplate.update(sql,openid,college,teacherName);
    }
    //根据id查询所有
    public List<Course> findCourseAllByid(int id){
        String sql="SELECT a.id,a.openid,a.name,a.college,a.major,a.grade,a.teacherName,a.count,a.locale FROM Course a ,Course b WHERE a.name=b.name AND a.major=b.major AND b.id=?";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        List<Course> courses=this.jdbcTemplate.query(sql,new Object[]{id},rowMapper);
        return courses;
    }
    public List<Course> findByCount(int count){
        String sql="SELECT id,openid,name,college,major,grade,teacherName,count,locale FROM Course WHERE count=?";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        List<Course> courses=this.jdbcTemplate.query(sql,new Object[]{count},rowMapper);
        return courses;
    }
    public List<Course> findAll(){
        String sql="SELECT id,openid,name,college,major,grade,teacherName,count,locale FROM Course";
        RowMapper<Course> rowMapper=new BeanPropertyRowMapper<>(Course.class);
        List<Course> courses=this.jdbcTemplate.query(sql,rowMapper);
        return courses;
    }
    public int[] batchInsert(final List<Course> courses){
        List<Object[]> batch=new ArrayList<>();
        for (Course course:courses){
            Object[] values=new Object[]{
                course.getName(),
                course.getCollege(),
                course.getMajor(),
                course.getGrade(),
                course.getTeacherName(),
                course.getCount(),
                course.getLocale(),
            };
            batch.add(values);
        }
        return jdbcTemplate.batchUpdate("INSERT INTO course(name, college, major, grade, teacherName, count, locale) VALUES (?,?,?,?,?,?,?)",batch);
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
