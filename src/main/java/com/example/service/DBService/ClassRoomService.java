package com.example.service.DBService;

import com.example.dao.entity.ClassRoom;
import com.example.dao.impl.ClassRoomDao;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/7.
 *
 */
@Service
public class ClassRoomService {
    @Autowired
    private ClassRoomDao classRoomDao;

    public List<ClassRoom> findAll(){
       return this.classRoomDao.findAll();
    }
    public int save(ClassRoom classRoom){
        return this.classRoomDao.save(classRoom);
    }
    public ClassRoom findByNumber(String number){
        return this.classRoomDao.findByNumber(number);
    }
}
