package com.collegemanagement.feesmanagement.controller;

import com.collegemanagement.feesmanagement.entity.Course;
import com.collegemanagement.feesmanagement.entity.Student;
import com.collegemanagement.feesmanagement.response.ApiResponseHandler;
import com.collegemanagement.feesmanagement.services.CourseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private CourseServices services;


    @PostMapping
    public ResponseEntity<ApiResponseHandler<Course>> insertCourse(@RequestBody Course course){
        Course savedCourse = services.insertCourse(course);
        ApiResponseHandler<Course> response = new ApiResponseHandler<>(
                true,
                "Course is added successfully.",
                savedCourse
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponseHandler<List<Course>>> fetchAllCourse(){
        List<Course> courses =  services.fetchCourses();
        ApiResponseHandler<List<Course>> response = new ApiResponseHandler<>(
                true,
                "List of courses.",
                courses
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseHandler<Course>> fetchCourseById(@PathVariable("id") Integer id){
        Course course = services.fecthCourseById(id);
        ApiResponseHandler<Course> response = new ApiResponseHandler<>(
                true,
                "Course with id "+id+" is found.",
                course
        );
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseHandler<Course>> updateCourse(@PathVariable Integer id, @RequestBody Course course){
        Course updatedCourse = services.updateCourse(id, course);
        ApiResponseHandler<Course> response = new ApiResponseHandler<>(
                true,
                "Course with id "+id+" updated successfully.",
                updatedCourse
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseHandler<Course>> deleteCourse(@PathVariable Integer id){
        services.removeCourse(id);
        ApiResponseHandler<Course> response = new ApiResponseHandler<>(
                true,
                "Course with id "+id+" is removed from the server.",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
