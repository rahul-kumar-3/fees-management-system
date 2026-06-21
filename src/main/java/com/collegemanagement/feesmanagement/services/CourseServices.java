package com.collegemanagement.feesmanagement.services;

import com.collegemanagement.feesmanagement.entity.Course;

import java.util.List;

public interface CourseServices {
    public Course insertCourse(Course course);
    public List<Course> fetchCourses();
    public Course fecthCourseById(Integer id);
    public Course updateCourse(Integer id, Course course);
    public void removeCourse(Integer id);
}
