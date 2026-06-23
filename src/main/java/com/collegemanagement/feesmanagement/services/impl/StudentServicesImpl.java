package com.collegemanagement.feesmanagement.services.impl;


import com.collegemanagement.feesmanagement.entity.Course;
import com.collegemanagement.feesmanagement.entity.Student;
import com.collegemanagement.feesmanagement.exception.CourseNotFoundException;
import com.collegemanagement.feesmanagement.exception.StudentNotFoundException;
import com.collegemanagement.feesmanagement.repository.CourseRepository;
import com.collegemanagement.feesmanagement.repository.StudentRepository;
import com.collegemanagement.feesmanagement.services.StudentServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServicesImpl implements StudentServices {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Override
    public Student insertStudent(Integer courseId, Student student) {
        Course course = courseRepository.findById(courseId).get();
        if(course == null){
            throw new CourseNotFoundException("course with id "+courseId+" doesn't exist");
        }
        student.setCourse(course);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> fetchAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student fetchStudentById(Integer id) {

        return studentRepository.findById(id).orElseThrow(()-> new  StudentNotFoundException("student with id "+id+" doesn't exist."));
    }

    @Override
    public Student updateStudentDetails(Integer id, Student student) {
        Student existingStudent = studentRepository.getReferenceById(id);
        if(existingStudent == null){
            throw new StudentNotFoundException("Student with id "+id+" doesn't exist.");
        }
        if(student.getAdmissionNumber() != null){
            existingStudent.setAdmissionNumber(student.getAdmissionNumber());
        }
        if(student.getFirstName() != null){
            existingStudent.setFirstName(student.getFirstName());
        }
        if(student.getLastName() != null){
            existingStudent.setLastName(student.getLastName());
        }
        if(student.getEmail() != null){
            existingStudent.setEmail(student.getEmail());
        }
        if(student.getPhone() != null){
            existingStudent.setPhone(student.getPhone());
        }
        if(student.getDateOfBirth() != null){
            existingStudent.setDateOfBirth(student.getDateOfBirth());
        }
        if(student.getGender() != null){
            existingStudent.setGender(student.getGender());
        }
        if(student.getAddress() != null){
            existingStudent.setAddress(student.getAddress());
        }
        if(student.getAdmissionDate() != null){
            existingStudent.setAdmissionDate(student.getAdmissionDate());
        }
        return studentRepository.save(existingStudent);
    }

    @Override
    public void removeStudent(Integer id) {
        Student student = studentRepository.getReferenceById(id);
        if(student == null){
            throw new StudentNotFoundException("student with id "+id+" doesn't exist.");
        }
        studentRepository.delete(student);
    }

}
