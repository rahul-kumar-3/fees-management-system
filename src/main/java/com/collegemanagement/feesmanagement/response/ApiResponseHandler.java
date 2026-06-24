package com.collegemanagement.feesmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseHandler<T> {
    private LocalDateTime timestamp;
    private boolean status;
    private String message;
    private T data;

    public ApiResponseHandler(boolean status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
