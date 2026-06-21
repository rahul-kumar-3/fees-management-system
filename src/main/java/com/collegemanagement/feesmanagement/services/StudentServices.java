package com.collegemanagement.feesmanagement.services;

import com.collegemanagement.feesmanagement.entity.Student;

import java.util.List;

public interface StudentServices {
    public Student insertStudent(Integer courseId, Student student);
    public List<Student> fetchAllStudent();
    public Student fetchStudentById(Integer id);
    public Student updateStudentDetails(Integer id, Student student);
    public void removeStudent(Integer id);
}
