package com.example.dao.impl;

import com.example.dao.entity.Leave;
import com.example.dao.entity.Student;
import com.example.dao.entity.StudentPhone;
import com.example.entity.LeaveView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/1
 * 请假名单.
 */
@Repository
public class LeaveDao {
    @SuppressWarnings("all")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Leave leave){

        String sql="INSERT INTO t_leave(openid,cause,type,startDate,endDate,section,local_1,local_2,urgentName,urgentPhone,state,openidTeacher)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        return this.jdbcTemplate.update(sql,leave.getOpenid(),leave.getCause(),leave.getType(),leave.getStartDate(),leave.getEndDate(),leave.getSection(),leave.getLocal_1(),leave.getLocal_2(),leave.getUrgentName(),leave.getUrgentName(),leave.getState(),leave.getOpenidTeacher());
    }

    public List<Leave> findByOpenid(String openid){
        String sql="SELECT id, openid,cause,type,startDate,endDate,section,local_1,local_2,urgentName,urgentPhone,state FROM t_leave WHERE openid=?";
        RowMapper<Leave> rowMapper=new BeanPropertyRowMapper<>(Leave.class);
        List<Leave> leaves=this.jdbcTemplate.query(sql,new Object[]{openid},rowMapper);
        return leaves;
    }
    public int update(int id,int state){
        String sql="UPDATE t_leave SET state=? WHERE id=?";
        return  this.jdbcTemplate.update(sql,state,id);
    }

    public List<LeaveView> findByMajorAndGrade(String major,String grade){
        String sql="SELECT * FROM student,studentPhone,t_leave WHERE student.sno=studentPhone.sno AND studentPhone.openid=t_leave.openid AND student.major=? AND student.grade=?";
        return this.jdbcTemplate.query(sql,new Object[]{major,grade},new LeaveMapper());
    }

    public List<LeaveView> findByOpenID(String openid){
        String sql="SELECT * FROM student,studentPhone,t_leave WHERE student.sno=studentPhone.sno AND studentPhone.openid=t_leave.openid AND studentPhone.openid=?";
        return this.jdbcTemplate.query(sql,new Object[]{openid},new LeaveMapper());
    }

    public int sumContent(String openid){
        String sql="SELECT count(*) FROM student,studentPhone,t_leave WHERE student.sno=studentPhone.sno AND studentPhone.openid=t_leave.openid AND t_leave.openidTeacher=?";
        return this.jdbcTemplate.queryForObject(sql,new Object[]{openid},Integer.class);
    }

    //根据openid查询 分页
    public List<LeaveView> findLimitPage(String openid,int m,int n){
        String sql="SELECT * FROM student,studentPhone,t_leave WHERE student.sno=studentPhone.sno AND studentPhone.openid=t_leave.openid AND t_leave.openidTeacher=? LIMIT ?,?";
        return this.jdbcTemplate.query(sql,new Object[]{openid,m,n},new LeaveMapper());
    }
//    public List<LeaveView> LeaveView

    public static final class LeaveMapper implements RowMapper<LeaveView>{

          @Override
        public LeaveView mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student=new Student();
            student.setSno(resultSet.getString("student.sno"));
            student.setName(resultSet.getString("student.name"));
//            student.setSex(resultSet.getInt(resultSet.getInt("student.sex")));
            student.setCollege(resultSet.getString("student.college"));
            student.setMajor(resultSet.getString("student.major"));
            student.setGrade(resultSet.getString("student.grade"));
            StudentPhone studentPhone=new StudentPhone();
            studentPhone.setId(resultSet.getInt("studentPhone.id"));
            studentPhone.setOpenid(resultSet.getString("studentPhone.openid"));
            studentPhone.setState(resultSet.getString("studentPhone.state"));
            Leave leave=new Leave();
            leave.setId(resultSet.getInt("t_leave.id"));
            leave.setOpenid(resultSet.getString("t_leave.openid"));
            leave.setType(resultSet.getInt("t_leave.type"));
            leave.setCause(resultSet.getString("t_leave.cause"));
            leave.setStartDate(resultSet.getDate("t_leave.startDate"));
            leave.setEndDate(resultSet.getDate("t_leave.endDate"));
            leave.setSection(resultSet.getInt("t_leave.section"));
            leave.setLocal_1(resultSet.getInt("t_leave.local_1"));
            leave.setLocal_2(resultSet.getString("t_leave.local_2"));
            leave.setUrgentName(resultSet.getString("t_leave.urgentName"));
            leave.setUrgentPhone(resultSet.getString("t_leave.urgentPhone"));
            leave.setState(resultSet.getInt("t_leave.state"));
            leave.setOpenidTeacher(resultSet.getString("openidTeacher"));
            LeaveView leaveView=new LeaveView(student,studentPhone,leave);
            return leaveView;
        }
    }
}
