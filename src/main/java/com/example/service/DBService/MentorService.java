package com.example.service.DBService;

import com.example.dao.entity.Mentor;
import com.example.dao.impl.MentorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/14.
 *
 */
@Service
public class MentorService {
    @Autowired
    private MentorDao mentorDao;

    public int save(Mentor mentor){
        return mentorDao.save(mentor);
    }

    public Mentor findByMajorAndGrade(String major,String grade){
        return mentorDao.findByMajorAndGrade(major,grade);
    }

    public List<Mentor> findByName(String name){

        return mentorDao.findByName(name);
    }

    public int update(String openid,String name){
        return mentorDao.update(openid,name);
    }

    public int updateAll(String openid){
        return this.mentorDao.uplateAll(openid);
    }
}
