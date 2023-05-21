package com.task.server.errror;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        // Customize the error response
        String errorMessage = "An error occurred: " + ex.getMessage();
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", errorMessage);
        responseBody.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBody.put("data", null);
        responseBody.put("message", "ERROR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    // @ExceptionHandler(RuntimeException.class)
    // public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
    //     // Customize the error response for a specific exception type
    //     String errorMessage = "A runtime exception occurred: " + ex.getMessage();
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    // }
}
