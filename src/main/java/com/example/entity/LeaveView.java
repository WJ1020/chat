package com.example.entity;

import com.example.dao.entity.Leave;
import com.example.dao.entity.Student;
import com.example.dao.entity.StudentPhone;

/**
 * Created by WangShiXiang on 2017/4/11.
 * 这是显示请假人员需要的数据
 */
public class LeaveView {

    private Student student;
    private StudentPhone studentPhone;
    private Leave leave;

    public LeaveView(Student student, StudentPhone studentPhone, Leave leave) {
        this.student = student;
        this.studentPhone = studentPhone;
        this.leave = leave;
    }
    public LeaveView(){}

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentPhone getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(StudentPhone studentPhone) {
        this.studentPhone = studentPhone;
    }

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }
}
