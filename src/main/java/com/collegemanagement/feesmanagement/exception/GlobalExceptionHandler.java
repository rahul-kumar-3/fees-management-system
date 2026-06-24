package com.collegemanagement.feesmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleStudentNotFoundException(StudentNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                false,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                ex
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleStudentNotFoundException(CourseNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                false,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                ex
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeesRecordNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleStudentNotFoundException(FeesRecordNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                false,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                ex
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
