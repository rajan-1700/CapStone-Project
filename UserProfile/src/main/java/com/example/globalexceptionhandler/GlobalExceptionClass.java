package com.example.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionClass {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            map.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> userAlreadyThere(UserAlreadyExistException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
