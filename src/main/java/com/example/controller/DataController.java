package com.example.controller;

import com.example.dao.entity.Absences;
import com.example.dao.entity.Student;
import com.example.service.DBService.AbsencesService;
import com.example.service.DBService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangShiXiang on 2017/3/17.
 * 对RESTFUL风格的请求进行处理。
 */
@RestController
public class DataController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AbsencesService absencesService;

    /**
     * 查询全部的学生
     * @return 返回json格式的数据
     */
    @RequestMapping(value = "/student",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Student> findAllStudent(){
        List<Student> students=this.studentService.findAll();
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
    @RequestMapping(value = "/no_subject",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String noSubject(@RequestParam("sno") String sno){
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
}
