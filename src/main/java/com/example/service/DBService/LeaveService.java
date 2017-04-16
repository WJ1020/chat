package com.example.service.DBService;

import com.example.dao.entity.Leave;
import com.example.dao.impl.LeaveDao;
import com.example.entity.LeaveView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.ListView;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/12.
 *
 */
@Service
public class LeaveService {
    @Autowired
    private LeaveDao leaveDao;
    public int save(Leave leave){
        return this.leaveDao.save(leave);
    }

    public List<LeaveView> findByMajorAndGrade(String major,String grade){

        return this.leaveDao.findByMajorAndGrade(major,grade);
    }

    public List<Leave> findByOpenId(String openid){
        return this.leaveDao.findByOpenid(openid);
    }
    public List<LeaveView> findLeaveViewFindById(String openid){

        return this.leaveDao.findByOpenID(openid);
    }

    public int update(int id,int state){
        return this.leaveDao.update(id,state);
    }

    public List<LeaveView> findByOpenidAndPage(String openid,int page){
        int sum=this.leaveDao.sumContent(openid);
        int count=5;
        int m,n;
        m=(page-1)*count;
        if((page-1)*count>sum){
            return null;
        }else if (page*count>sum){
            n=sum-(page-1)*count;
        }else {
            n=5;
        }
        return this.leaveDao.findLimitPage(openid,m,n);
    }
}
