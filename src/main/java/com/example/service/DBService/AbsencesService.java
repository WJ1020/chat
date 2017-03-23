package com.example.service.DBService;

import com.example.dao.entity.Absences;
import com.example.dao.impl.AbsencesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/18.
 * 用户信息的业务逻辑层
 */
@Service
public class AbsencesService {

    @Autowired
    private AbsencesDao absencesDao;

    public List<Absences> findAll(){
        return absencesDao.findAll();
    }
    public List<Absences> findByCourse(String course){
        return absencesDao.findByCourse(course);
    }
}
