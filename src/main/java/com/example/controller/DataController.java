package com.example.controller;

import com.example.dao.entity.*;
import com.example.entity.LeaveView;
import com.example.service.DBService.*;
import com.example.service.SendShortMessage;
import com.example.util.JsonUtil;
import com.example.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by WangShiXiang on 2017/3/17.
 * 对数据进行处理
 * QQ825305769
 */
@RestController
@RequestMapping("data")
public class DataController {

    private StudentService studentService;

    private AbsencesService absencesService;

    private CourseService courseService;

    private TeacherService teacherService;

    private StudentScoreService studentScoreService;

    private ClassRoomService classRoomService;

    private SendShortMessage sendShortMessage;

    private LeaveService leaveService;

    private StudentPhoneService studentPhoneService;

    private MentorService mentorService;
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    public void setAbsencesService(AbsencesService absencesService) {
        this.absencesService = absencesService;
    }
    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @Autowired
    public void setStudentScoreService(StudentScoreService studentScoreService) {
        this.studentScoreService = studentScoreService;
    }
    @Autowired
    public void setClassRoomService(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }
    @Autowired
    public void setSendShortMessage(SendShortMessage sendShortMessage) {
        this.sendShortMessage = sendShortMessage;
    }
    @Autowired
    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    @Autowired
    public void setStudentPhoneService(StudentPhoneService studentPhoneService) {
        this.studentPhoneService = studentPhoneService;
    }
    @Autowired
    public void setMentorService(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    /**
     * 查询学生表的全部的学生
     * @return 返回json格式的数据
     */
    @RequestMapping(value = "/student",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Student> findAllStudent(){
        return this.studentService.findAll();
    }
    /**
     * 首先根据此用户的openid查询课程表，如果查询不到则返回空字符串，此时微信端应该
     * 跳转到绑定页面  即教师的openid和课程绑定（页面的业务逻辑在ViewController控制器）
     * 如果查询到课程表之后，根据当时的时间来确定次节课应该上什么。根据次课程的年级和专业来返回学生的名单
     * @param openid 微信公众号的openid
     * @return 返回所有的学生
     */
    @RequestMapping(value = "/openid",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Object findAllStudent(@RequestParam("openid") String openid, HttpSession httpSession){
        List<Course> courses=this.courseService.findNowCourse(openid, TimeUtil.getSection());
        if (courses.size()>0){
            List<Student> students=this.studentService.findByMajorAndGrade(courses.get(0).getMajor(),courses.get(0).getGrade());
            httpSession.setAttribute("course",courses.get(0));
            return students;
        }
        return null;
    }
    //随机点名
    @RequestMapping(value = "/randomstudent",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Student findRandomStudent(@RequestParam("openid") String openid, HttpSession httpSession){
        List<Course> courses=this.courseService.findNowCourse(openid, TimeUtil.getSection());
        if (courses.size()>0){
            List<Student> students=this.studentService.findByMajorAndGrade(courses.get(0).getMajor(),courses.get(0).getGrade());
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
        return studentScoreService.findByOpenidAndCourseName(course.getOpenid(),course.getName(),course.getMajor());
    }
    @RequestMapping(value = "/student/{sno}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Student findBySno(@PathVariable("sno") String sno){
        return this.studentService.findBySno(sno);
    }
    @RequestMapping(value = "/student/{sno}",method = RequestMethod.DELETE,produces ="application/json;charset=UTF-8" )
    public int deleteStudent(@PathVariable("sno") String sno){
        return this.studentService.delete(sno);
    }
    @RequestMapping(value = "/student",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public int save(@RequestBody Student student){
        return this.studentService.save(student);
    }

    /**
     * 设置学生的缺课信息
     * @param sno 学号
     * @param state 0请假，2旷课，3，迟到
     * @param httpSession 由spring自动注入
     * @return 返回成功保存的数量
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
         return absencesService.findByCourse(course);
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
    @SuppressWarnings("all")//看着警告不爽
    @RequestMapping(value = "/findfreeclassroom",method =RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<ClassRoom> findFreeClassRoom(){
        List<ClassRoom> classRooms=classRoomService.findAll();
        List<Course> courses=courseService.findByCount(TimeUtil.getSection());
        classRooms.removeAll(courses);
        return classRooms;
    }
    //根据指定时间查询教室（传入的数据格式为:2017-02-03-12-22 年-月-日-时-分）
    @SuppressWarnings("all")//有个警告，不会解决
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
        return courseService.findByOpenId(openid);
    }
    @RequestMapping(value = "/sendshortmessage",method =RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    public int sendShortMessageUrl(@RequestParam("openid") String openid,@RequestParam("major") String major,@RequestParam("grade") String grade,@RequestParam("text") String text){
        return sendShortMessage.SendMessage(openid,major,grade,text);
    }
    @RequestMapping(value = "/askforleave",method =RequestMethod.PUT,produces = "application/json;charset=UTF-8")
    public int askForLeave(@RequestBody Map map){
        Date startDate=TimeUtil.switchDate(map.get("startDate").toString());
        Date endDate=TimeUtil.switchDate(map.get("endDate").toString());
        String openid=map.get("openid").toString();
        StudentPhone studentPhone=studentPhoneService.findByOpenid(openid);
        if (studentPhone==null){//此处应该重新设计
            return -1;//不存在 //如果你有幸重写我的代码请务必重写此功能的业务逻辑
        }
        Student student=studentService.findBySno(studentPhone.getSno());
        if (student==null){
            return -1;
        }
        Mentor mentor=mentorService.findByMajorAndGrade(student.getMajor(),student.getGrade());
        //int id, String openid, String cause, int type, Date startDate, Date endDate, int section, int local_1, String local_2, String urgentName, String urgentPhone
        Leave leave=new Leave(map.get("openid").toString(),map.get("cause").toString(),Integer.parseInt(map.get("type").toString()),startDate,endDate,Integer.parseInt(map.get("section").toString()),Integer.parseInt(map.get("local_1").toString()),map.get("local_2").toString(),map.get("urgentName").toString(),map.get("urgentPhone").toString(),0);
        leave.setOpenidTeacher(mentor.getOpenid());
        return leaveService.save(leave);
    }
    @RequestMapping(value = "findleavebyopenid",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Leave> findLeaveByOpenId(@RequestParam("openid") String openid){
       return leaveService.findByOpenId(openid);
    }
    @RequestMapping(value = "updateleavestate",method = RequestMethod.POST)
    public int updateLeaveState(@RequestParam("id") int id,@RequestParam("state") int state){
        return this.leaveService.update(id,state);
    }
    @RequestMapping(value = "/findleavelist",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public List<LeaveView> findListViewByOpenId(String openid){
        return this.leaveService.findLeaveViewFindById(openid);
    }
    //分页查询
    @RequestMapping(value="findlimitleavelist",method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
    public List<LeaveView> findLimitListViewByOpenid(String openid,int page){
        return this.leaveService.findByOpenidAndPage(openid,page);
    }
    @RequestMapping(value="/findmentorbyname",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Mentor> findMentorByName(String name){
        return this.mentorService.findByName(name);
    }
    @RequestMapping(value="/bindmentorbyname",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public int bindMentorOpenid(@RequestParam("name") String name,@RequestParam("openid") String openid){
       return   this.mentorService.update(openid,name);
    }
    @SuppressWarnings("all")
    @RequestMapping(value = "/batchinsertstudent",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public int[] batchInsertStudent(@RequestBody String json){
        try {
            json= URLDecoder.decode(json,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Student> students= JsonUtil.JsonToList(Student.class,json);
        return studentService.batchInsert(students);

    }
}
