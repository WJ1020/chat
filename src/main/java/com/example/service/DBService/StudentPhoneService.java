package com.example.service.DBService;

import com.example.dao.entity.StudentPhone;
import com.example.dao.impl.StudentPhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/10.
 *
 */
@Service
public class StudentPhoneService {
    @Autowired
    private StudentPhoneDao studentPhoneDao;

    public int save(StudentPhone studentPhone){
       return this.studentPhoneDao.save(studentPhone);
    }
    public List<StudentPhone> findMajorAndGrade(String major,String grade){
        return this.studentPhoneDao.findByMajor(major,grade);
    }
}
