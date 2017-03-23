package com.example.service.DBService;

import com.example.dao.entity.Student;
import com.example.dao.impl.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/17.
 * 学生类的服务
 */
@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> findAll(){
        return this.studentDao.findAll();
    }
    public Student findBySno(String sno){
        return this.studentDao.findBySno(sno);
    }

    public int save(Student student){
        return  this.studentDao.save(student);
    }

    public int delete(String sno){
        return this.studentDao.delete(sno);
    }
}
