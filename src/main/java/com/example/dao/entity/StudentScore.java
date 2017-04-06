package com.example.dao.entity;

/**
 * Created by WangShiXiang on 2017/4/4.
 * 课堂点名学生的成绩
 */
public class StudentScore {
    private int id;
    private String openid;
    //专业
    private String major;
    //课程名
    private String courseName;
    //学号
    private String sno;
    //学生姓名
    private String studentName;
    //分数
    private int courseScore;

    public StudentScore(){

    }

    public StudentScore(int id, String openid, String major, String courseName, String sno, String studentName, int courseScore) {
        this.id = id;
        this.openid = openid;
        this.major = major;
        this.courseName = courseName;
        this.sno = sno;
        this.studentName = studentName;
        this.courseScore = courseScore;
    }

    public StudentScore(String openid, String major, String courseName, String sno, String studentName, int courseScore) {
        this.openid = openid;
        this.major = major;
        this.courseName = courseName;
        this.sno = sno;
        this.studentName = studentName;
        this.courseScore = courseScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(int courseScore) {
        this.courseScore = courseScore;
    }
}
