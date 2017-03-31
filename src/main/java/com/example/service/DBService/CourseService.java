package com.example.service.DBService;

import com.example.dao.entity.Course;
import com.example.dao.impl.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/29.
 *
 */
@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    public int save(Course course){
       return courseDao.save(course);
    }

    public Course findById(int id){
        return courseDao.findById(id);
    }

    public List<Course> findByOpenId(String openid){
        return courseDao.findByOpenid(openid);
    }

    public List<Course> findNowCourse(String openid,int i){
        return courseDao.findNowCourse(openid,i);
    }
    public void batchUpdate(List<Course> courses){
        courseDao.batchUpdateOpenid(courses);
    }
}
