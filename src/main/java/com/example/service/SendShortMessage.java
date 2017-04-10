package com.example.service;

import com.example.dao.entity.StudentPhone;
import com.example.dao.entity.Teacher;
import com.example.service.DBService.StudentPhoneService;
import com.example.service.DBService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/10.
 * 短信发送服务
 */
@Service
public class SendShortMessage {
    @Autowired
    private StudentPhoneService studentPhoneService;
    @Autowired
    private TeacherService teacherService;

    public int SendMessage(String openid,String major,String grade,String text){
        Teacher teacher=this.teacherService.findByOpenID(openid);
        if (teacher==null){
            return -1;
        }
        List<StudentPhone> studentPhones=studentPhoneService.findMajorAndGrade(major,grade);
        if (studentPhones.isEmpty()){
            return 0;
        }
        int count=0;
        if (studentPhones.size()<=100){
            int i=0;
            String[] arr=null;
            arr=new String[studentPhones.size()];
            for (StudentPhone studentPhone:studentPhones){
                arr[i]=studentPhone.getPhone();
                i++;
            }
            count= com.example.util.SendShortMessage.send(text,arr);
        }else {
            //当短信大于100条时在这处理
            int index=0;
            int size=studentPhones.size();
            String[] arr=null;
            do {
                if(size>100){
                    arr=new String[100];
                    int z=0;
                    for (int i=index;i<index+arr.length;i++){
                        arr[z]=studentPhones.get(i).getPhone();
                        z++;
                    }
                    count+=com.example.util.SendShortMessage.send(text,arr);
                    z=0;
                    index=index+arr.length;
                    size-=100;
                }else {
                    arr=new String[size];
                    int z=0;
                    for (int i=index;i<index+arr.length;i++){
                        arr[z]=studentPhones.get(i).getPhone();
                    }
                    count+=com.example.util.SendShortMessage.send(text,arr);
                    z=0;
                    index=index+arr.length;
                    size-=100;
                }
            }while (size>0);
        }
       return count;
    }
}
