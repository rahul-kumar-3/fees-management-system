package com.collegemanagement.feesmanagement.services.impl;

import com.collegemanagement.feesmanagement.entity.Course;
import com.collegemanagement.feesmanagement.repository.CourseRepository;
import com.collegemanagement.feesmanagement.services.CourseServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServicesImpl implements CourseServices {
    private CourseRepository repository;

    public CourseServicesImpl(CourseRepository repository) {
        this.repository = repository;
    }


    @Override
    public Course insertCourse(Course course) {
        return repository.save(course);
    }

    @Override
    public List<Course> fetchCourses() {
        return repository.findAll();
    }

    @Override
    public Course fecthCourseById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Course updateCourse(Integer id, Course course) {
        Course existingCourse = repository.findById(id).get();
        if(course.getCourseCode() != null){
            existingCourse.setCourseCode(course.getCourseCode());
        }

        if(course.getCourseName() != null){
            existingCourse.setCourseName(course.getCourseName());
        }

        if(course.getTotalFees() != null){
            existingCourse.setTotalFees(course.getTotalFees());
        }

        if(course.getDurationInMonths() != null){
            existingCourse.setDurationInMonths(course.getDurationInMonths());
        }
        return repository.save(existingCourse);
    }

    @Override
    public void removeCourse(Integer id) {
        repository.deleteById(id);
    }
}
