package com.collegemanagement.feesmanagement.controller;

import com.collegemanagement.feesmanagement.entity.Student;
import com.collegemanagement.feesmanagement.exception.StudentNotFoundException;
import com.collegemanagement.feesmanagement.services.StudentServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private StudentServices services;
    @GetMapping("/students")
    public List<Student> fetchAllStudents(){
        return services.fetchAllStudent();
    }

    @GetMapping("/students/{id}")
    public Student fetchStudentById(@PathVariable Integer id){

        return services.fetchStudentById(id);
    }

    @PutMapping("students/{id}")
    public Student updateStudentDetails(@PathVariable Integer id, @RequestBody Student student){
        return services.updateStudentDetails(id, student);
    }

    @DeleteMapping("students/{id}")
    public void removeStudent(@PathVariable Integer id){
        services.removeStudent(id);
    }

    @PostMapping("/{cid}/students")
    public Student insertStudent(@PathVariable Integer cid, @RequestBody Student student){
        return services.insertStudent(cid, student);
    }
}
