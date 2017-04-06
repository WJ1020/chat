package com.example.service.DBService;

import com.example.dao.entity.StudentScore;
import com.example.dao.impl.StudentScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/5.
 *
 */
@Service
public class StudentScoreService {
    @Autowired
    private StudentScoreDao studentScoreDao;

    public int save(StudentScore studentScore){
        return this.studentScoreDao.save(studentScore);
    }

    public List<StudentScore> findByOpenidAndMajor(String openid,String major){
        return this.studentScoreDao.findByOpenidAndMajor(openid,major);
    }
    public List<StudentScore> findByOpenidAndCourseName(String openid,String courseName,String major){
        return this.studentScoreDao.findByOpenidAndCourseName(openid,courseName,major);
    }
}
