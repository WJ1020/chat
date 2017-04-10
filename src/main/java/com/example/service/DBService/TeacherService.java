package com.example.service.DBService;

import com.example.dao.entity.Teacher;
import com.example.dao.impl.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/1.
 *
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;
    public int save(Teacher teacher){
        return this.teacherDao.save(teacher);
    }
    public Teacher findById(int id){
        return this.teacherDao.findById(id);
    }
    public List<Teacher> findByName(String name){
        return this.teacherDao.findByName(name);
    }
    public int setOpenid(int id,String openid){
        return this.teacherDao.setOpenid(id,openid);
    }
    public Teacher findByOpenID(String openid){
        return this.teacherDao.findByOpenid(openid);
    }

}
