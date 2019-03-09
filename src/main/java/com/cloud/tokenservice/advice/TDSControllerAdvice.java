package com.cloud.tokenservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TDSControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> notFoundException(final Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("error sri", HttpStatus.NOT_FOUND);
    }
}
