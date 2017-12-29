package com.example.view;

import com.example.dao.entity.ClassRoom;
import com.example.dao.entity.Course;
import org.codehaus.groovy.runtime.StringGroovyMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/4/7.
 * 测试
 */
public class Main {
    public static int test(int i){
        switch (i){
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 0;
        }
    }

    public static void main(String[] args){

        System.out.println(test(2));

//        Course course=new Course();course.setLocale("1001");
//        Course course1=new Course();course1.setLocale("1002");
//        List<Course> courses=new ArrayList<>();
//        courses.add(course);courses.add(course1);
//        ClassRoom classRoom=new ClassRoom();classRoom.setNumber("1001");
//        ClassRoom classRoom1=new ClassRoom();classRoom1.setNumber("1002");
//        ClassRoom classRoom2=new ClassRoom();classRoom2.setNumber("1003");
//        ClassRoom classRoom3=new ClassRoom();classRoom3.setNumber("1005");
//        List<ClassRoom> classRooms=new ArrayList<>();
//        classRooms.add(classRoom);classRooms.add(classRoom1);classRooms.add(classRoom2);classRooms.add(classRoom3);
//        System.out.println(classRooms.removeAll(courses));
//        System.out.println(classRooms);
//        String str="<div class=\"weui-footer\">\n" +
//                "  <p class=\"weui-footer__text\">人家是有底线的</p>\n" +
//                "</div>";
    }
}
