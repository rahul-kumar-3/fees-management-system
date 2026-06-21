package com.collegemanagement.feesmanagement.controller;

import com.collegemanagement.feesmanagement.entity.Course;
import com.collegemanagement.feesmanagement.services.CourseServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private CourseServices services;


    @PostMapping
    public Course insertCourse(@RequestBody Course course){
        return services.insertCourse(course);
    }

    @GetMapping
    public List<Course> fetchAllCourse(){
        return services.fetchCourses();
    }

    @GetMapping("/{id}")
    public Course fetchCourseById(@PathVariable("id") Integer id){
       return services.fecthCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Integer id, @RequestBody Course course){
        return services.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Integer id){
        services.removeCourse(id);
    }

}
