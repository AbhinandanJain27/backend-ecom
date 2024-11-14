package com.Abhinandan.Ecommerce.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<?> handleUnauthorizedAccessException(UnauthorizedAccessException ex){
        return new ResponseEntity<>("Access Denied!" + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

}
