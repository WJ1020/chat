package com.example.dao.impl;

import com.example.dao.entity.Absences;
import com.example.dao.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/18.
 * 缺课信息
 */
@Repository
public class AbsencesDao {
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Absences absences){
        String sql="INSERT INTO absences (sno,course,date,state)VALUES (?,?,?,?)";
        return this.jdbcTemplate.update(sql,absences.getStudent().getSno(),absences.getCourse(),absences.getDate(),absences.getState());
    }
    public List<Absences> findByCourse(String course){
        String sql="SELECT id,student.sno,name,sex,college,major,grade,course,date,state FROM absences LEFT JOIN student ON student.sno=absences.sno WHERE course=?";
        List<Absences> absences=this.jdbcTemplate.query(sql,new Object[]{course},new AbsencesMapper());
        return absences;
    }
    public List<Absences> findAll(){
        String sql="SELECT id,student.sno,name,sex,college,major,grade,course,date,state FROM absences LEFT JOIN student ON student.sno=absences.sno";
        List<Absences> absences=this.jdbcTemplate.query(sql,new AbsencesMapper());
        return absences;
    }
    public static final class AbsencesMapper implements RowMapper<Absences>{
        @Override
        public Absences mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student=new Student(resultSet.getString("sno"),resultSet.getString("name"),resultSet.getInt("sex"),resultSet.getString("college"),resultSet.getString("major"),resultSet.getString("grade"));
            Absences absences=new Absences(student,resultSet.getString("course"),resultSet.getDate("date"),resultSet.getInt("state"));
            return absences;
        }
    }
}
