package com.example.controller;

import com.example.dao.entity.Absences;
import com.example.dao.entity.Course;
import com.example.dao.entity.Student;
import com.example.dao.entity.Teacher;
import com.example.service.DBService.AbsencesService;
import com.example.service.DBService.CourseService;
import com.example.service.DBService.StudentService;
import com.example.service.DBService.TeacherService;
import com.example.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/17.
 * 对RESTFUL风格的请求进行处理。
 */
@RestController
@RequestMapping("data")
public class DataController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AbsencesService absencesService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 查询学生表的全部的学生
     * @return 返回json格式的数据
     */
    @RequestMapping(value = "/student",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Student> findAllStudent(){
        List<Student> students=this.studentService.findAll();
        return students;
    }
    /**
     * 首先根据此用户的openid查询课程表，如果查询不到则返回空字符串，此时微信端应该
     * 跳转到绑定页面  即教师的openid和课程绑定（页面的业务逻辑在ViewController控制器）
     * 如果查询到课程表之后，根据当时的时间来确定次节课应该上什么。根据次课程的年级和专业来返回学生的名单
     * @param openid
     * @return
     */
    @RequestMapping(value = "/openid",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Object findAllStudent(@RequestParam("openid") String openid, HttpSession httpSession){
        List<Student> students=null;
        List<Course> courses=this.courseService.findNowCourse(openid, TimeUtil.getSection());
        if (courses.size()>0){
            students=this.studentService.findByMajor(courses.get(0).getMajor());
            httpSession.setAttribute("course",courses.get(0));
            return students;
        }
        return students;
    }
    @RequestMapping(value = "/student/{sno}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Student findBySno(@PathVariable("sno") String sno){
        Student student=this.studentService.findBySno(sno);
        return student;
    }
    @RequestMapping(value = "/student/{sno}",method = RequestMethod.DELETE,produces ="application/json;charset=UTF-8" )
    public int deleteStudent(@PathVariable("sno") String sno){
        int count=this.studentService.delete(sno);
        return count;
    }
    @RequestMapping(value = "/student",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public int save(@RequestBody Student student){
        int count=this.studentService.save(student);
        return count;
    }

    /**
     * 设置学生的缺课信息
     * @param sno 学号
     * @param state 0请假，2旷课，3，迟到
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/no_subject",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String noSubject(@RequestParam("sno") String sno,@RequestParam("state") Integer state,HttpSession httpSession){
        Course course=(Course)httpSession.getAttribute("course");
        Student student=new Student(sno);
        Absences absences=new Absences(student,String.valueOf(course.getId()),new Date(),state);
        absencesService.save(absences);
        return sno;
    }
    @RequestMapping(value = "/absences",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Absences> findAllAbsences(){
        return absencesService.findAll();
    }
    @RequestMapping(value = "/find_course/{course}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Absences> findByCourse(@PathVariable("course") String course){
        List<Absences> absences=absencesService.findByCourse(course);
        return absences;
    }

    /**
     * 用来将教师的微信id和用户名绑定，同时将openid和用户名绑定
     * @param openid openid
     * @param id 用户id
     * @return 绑定的课程数
     */
    @RequestMapping(value = "/setopenid",method = RequestMethod.POST)
    public int bind_openid(@RequestParam("openid") String openid,@RequestParam("id") int id){
        Teacher teacher=teacherService.findById(id);
        int count=courseService.updateOpenid(openid,teacher.getCollege(),teacher.getName());
        if (count>0){
            teacherService.setOpenid(id,openid);

        }
        return count;
    }
}
