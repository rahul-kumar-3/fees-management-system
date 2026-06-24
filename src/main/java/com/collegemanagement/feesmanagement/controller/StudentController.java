package com.collegemanagement.feesmanagement.controller;

import com.collegemanagement.feesmanagement.entity.Student;
import com.collegemanagement.feesmanagement.response.ApiResponseHandler;
import com.collegemanagement.feesmanagement.services.StudentServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class StudentController {
    private StudentServices services;
    @GetMapping("/students")
    public ResponseEntity<ApiResponseHandler<List<Student>>> fetchAllStudents(){
        List<Student> students = services.fetchAllStudent();
        ApiResponseHandler<List<Student>> response = new ApiResponseHandler<>(
                true,
                "List of students.",
                students
        );
        return new ResponseEntity<ApiResponseHandler<List<Student>>>(response, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ApiResponseHandler<Student>> fetchStudentById(@PathVariable Integer id){
        Student student = services.fetchStudentById(id);
        ApiResponseHandler<Student> response = new ApiResponseHandler<>(
                true,
                "Student with id "+id+" found successfuly.",
                student
        );
        return new ResponseEntity<ApiResponseHandler<Student>>(response, HttpStatus.OK);
    }

    @PutMapping("students/{id}")
    public ResponseEntity<ApiResponseHandler<Student>> updateStudentDetails(@PathVariable Integer id, @RequestBody Student student){
        Student updatedStudent = services.updateStudentDetails(id, student);
        ApiResponseHandler<Student> response = new ApiResponseHandler<>(
                true,
                "Student with id "+id+" updated successfuly.",
                updatedStudent
        );
        return new ResponseEntity<ApiResponseHandler<Student>>(response, HttpStatus.OK);
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<ApiResponseHandler<Student>> removeStudent(@PathVariable Integer id){
        services.removeStudent(id);
        ApiResponseHandler<Student> response = new ApiResponseHandler<>(
                true,
                "Student with id "+id+" removed from the server.",
                null
        );

        return new ResponseEntity<ApiResponseHandler<Student>>(response, HttpStatus.OK);

    }

    @PostMapping("/{cid}/students")
    public ResponseEntity<ApiResponseHandler<Student>> insertStudent(@PathVariable Integer cid, @RequestBody Student student){
        Student savedStudent = services.insertStudent(cid, student);
        ApiResponseHandler<Student> response = new ApiResponseHandler<>(
                true,
                "Student data saved successfuly.",
                savedStudent
        ) ;
        return new ResponseEntity<ApiResponseHandler<Student>>(response, HttpStatus.CREATED);
    }
    @GetMapping("/students/pending-amount")
    public ResponseEntity<ApiResponseHandler<List<Student>>> getPendingStudentsList(){
        List<Student> students = services.getPendingStudentsList();
        ApiResponseHandler<List<Student>> response = new ApiResponseHandler<>(
                true,
                "List of pending payment students.",
                students
        );
        return new ResponseEntity<ApiResponseHandler<List<Student>>>(response, HttpStatus.OK);
    }
}
