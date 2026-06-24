package com.collegemanagement.feesmanagement.services.impl;


import com.collegemanagement.feesmanagement.entity.Course;
import com.collegemanagement.feesmanagement.entity.Fees;
import com.collegemanagement.feesmanagement.entity.Student;
import com.collegemanagement.feesmanagement.exception.CourseNotFoundException;
import com.collegemanagement.feesmanagement.exception.StudentNotFoundException;
import com.collegemanagement.feesmanagement.repository.CourseRepository;
import com.collegemanagement.feesmanagement.repository.FeesRepository;
import com.collegemanagement.feesmanagement.repository.StudentRepository;
import com.collegemanagement.feesmanagement.response.ApiResponseHandler;
import com.collegemanagement.feesmanagement.services.StudentServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServicesImpl implements StudentServices {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private FeesRepository feesRepository;

    @Override
    public Student insertStudent(Integer courseId, Student student) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course with id "+courseId+" doesn't exist."));
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
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id "+id+" doesn't exist."));
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

        if(student.getCourse() != null){
            Course course = courseRepository.findById(student.getCourse().getCourseId()).orElseThrow(() -> new CourseNotFoundException("course with id "+student.getCourse().getCourseId()+" doesn't exist."));
            existingStudent.setCourse(course);
        }
        return studentRepository.save(existingStudent);
    }

    @Override
    public void removeStudent(Integer id) {
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("student with id "+id+" doesn't exist.");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getPendingStudentsList() {
        List<Fees> fees = feesRepository.findAll().stream()
                .filter(fees1 -> fees1.getRemainingAmount() != null && fees1.getRemainingAmount() > 0)
                .collect(Collectors.toList());


        return fees.stream()
                .map(Fees::getStudent)
                .distinct()
                .collect(Collectors.toList());
    }


}
