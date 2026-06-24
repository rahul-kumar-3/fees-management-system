package com.collegemanagement.feesmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private boolean success;
    private Integer status;
    private String error;
    private String message;
    private Throwable exception;

    public ErrorDetails(LocalDateTime timeStamp,boolean success, int status, String error, String message, Throwable ex) {
        this.timestamp = timeStamp;
        this.success = success;
        this.status = status;
        this.error = error;
        this.message = message;
        this.exception = ex;
    }
}
