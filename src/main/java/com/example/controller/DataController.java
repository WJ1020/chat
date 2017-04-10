package com.example.controller;

import com.example.dao.entity.*;
import com.example.service.DBService.*;
import com.example.service.SendShortMessage;
import com.example.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by WangShiXiang on 2017/3/17.
 * 对数据进行处理
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
    @Autowired
    private StudentScoreService studentScoreService;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private SendShortMessage sendShortMessage;

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
            students=this.studentService.findByMajorAndGrade(courses.get(0).getMajor(),courses.get(0).getGrade());
            httpSession.setAttribute("course",courses.get(0));
            return students;
        }
        return students;
    }
    //随机点名
    @RequestMapping(value = "/randomstudent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Student findRandomStudent(@RequestParam("openid") String openid, HttpSession httpSession){
        List<Student> students=null;
        List<Course> courses=this.courseService.findNowCourse(openid, TimeUtil.getSection());
        if (courses.size()>0){
            students=this.studentService.findByMajorAndGrade(courses.get(0).getMajor(),courses.get(0).getGrade());
            httpSession.setAttribute("course",courses.get(0));
            if (students.size()>0){
                int randomCount=(int)(Math.random()*students.size());
                return students.get(randomCount);
            }
        }
        return null;
    }
    //来给学生打分
    @RequestMapping(value="/setstudentscore",method = RequestMethod.PUT)
    public int setStudentScore(@RequestParam("openid") String openid,@RequestParam("student_name") String name,@RequestParam("student_sno") String sno,@RequestParam("student_score") int score,HttpSession httpSession){
        Course course=(Course) httpSession.getAttribute("course");
        StudentScore studentScore=new StudentScore(openid,course.getMajor(),course.getName(),sno,name,score);
        studentScoreService.save(studentScore);
        return score;
    }
    //查看学生打分情况
    @RequestMapping(value = "/findstudentscore",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<StudentScore> findByStudentScoreOpenidAndId(@RequestParam("course_id") int id){
        Course course=courseService.findById(id);
        List<StudentScore> studentScores=studentScoreService.findByOpenidAndCourseName(course.getOpenid(),course.getName(),course.getMajor());
        return studentScores;
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
    //查看所有缺课学生
    @RequestMapping(value = "/absences",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Absences> findAllAbsences(){
        return absencesService.findAll();
    }
    //根据课程id
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
    @RequestMapping(value = "/setopenid",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public int bind_openid(@RequestParam("openid") String openid,@RequestParam("id") int id){
        Teacher teacher=teacherService.findById(id);
        int count=courseService.updateOpenid(openid,teacher.getCollege(),teacher.getName());
        if (count>0){
            teacherService.setOpenid(id,openid);
        }
        return count;
    }

    @RequestMapping(value = "/findcoursebyopenid",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Course> findCourseByOpenid(@RequestParam("openid") String openid){
        return courseService.findCourseByOpenid(openid);
    }
    //根据其中一个课程的id查询到所有的课
    @RequestMapping(value = "/findabsencesstudent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8" )
    public List<Absences> findByAbsencesStudent(int id){
        List<Course> courses=courseService.findCourseAllByid(id);
        List<Absences> absences=null;
        for (Course course:courses){
            if (absences==null){
                absences=absencesService.findByCourse(String.valueOf(course.getId()));
            }else {
                List<Absences> a=absencesService.findByCourse(String.valueOf(course.getId()));
                absences.addAll(a);
            }
        }
        return absences;
    }
    @RequestMapping(value="/findclassroomall",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<ClassRoom> findByClassRoomAll(){
        return classRoomService.findAll();
    }
    //查询当时的空闲教室
    @RequestMapping(value = "/findfreeclassroom",method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<ClassRoom> findFreeClassRoom(){
        List<ClassRoom> classRooms=classRoomService.findAll();
        List<Course> courses=courseService.findByCount(TimeUtil.getSection());
        classRooms.removeAll(courses);
        return classRooms;
    }
    //根据指定时间查询教室（传入的数据格式为:2017-02-03-12-22 年-月-日-时-分）
    @RequestMapping(value = "/findspecifytimeclassroom",method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<ClassRoom> findSpecifyTimeClassRoom(@RequestParam("date") String str){
        String[] date=str.split("-");
        LocalDateTime localDateTime=LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(date[3]),Integer.parseInt(date[4]));
        List<ClassRoom> classRooms=classRoomService.findAll();
        List<Course> courses=courseService.findByCount(TimeUtil.getSpecifyTimeSection(localDateTime));
        classRooms.removeAll(courses);
        return classRooms;
    }
    //根据openid查询全部的
    @RequestMapping(value = "/findallcoursebyopenid",method =RequestMethod.GET,produces = "application/json;charset=UTF-8" )
    public List<Course> findAllCourseByOpenid(String openid){
        List<Course> courses=courseService.findByOpenId(openid);
        return courses;
    }
    @RequestMapping(value = "/sendshortmessage",method =RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    public int sendShortMessageUrl(@RequestParam("openid") String openid,@RequestParam("major") String major,@RequestParam("grade") String grade,@RequestParam("text") String text){
        int count=sendShortMessage.SendMessage(openid,major,grade,text);
        return count;
    }
}
