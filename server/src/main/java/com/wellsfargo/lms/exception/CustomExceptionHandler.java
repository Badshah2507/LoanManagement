package com.wellsfargo.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String, Object>> handleUserSaveException(UserNotFound ex) {
        Map<String, Object> body = new HashMap<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        body.put("timestamp", timestamp);
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<Map<String,Object>> handleDataNotFoundException(DataNotFound ex) {
        Map<String, Object> body = new HashMap<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        body.put("timestamp", timestamp);
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
