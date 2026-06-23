package com.collegemanagement.feesmanagement.services.impl;

import com.collegemanagement.feesmanagement.entity.Course;
import com.collegemanagement.feesmanagement.exception.CourseNotFoundException;
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

        return repository.findById(id).orElseThrow(()->new CourseNotFoundException("course with id "+id+" doesn't exist."));
    }

    @Override
    public Course updateCourse(Integer id, Course course) {

        Course existingCourse = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " doesn't exist"));
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
        if (!repository.existsById(id)) {
            throw new CourseNotFoundException("Course with id " + id + " doesn't exist.");
        }

        repository.deleteById(id);
    }
}
