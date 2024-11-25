package com.Abhinandan.Ecommerce.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<?> handleUnauthorizedAccessException(UnauthorizedAccessException ex){
        return new ResponseEntity<>("Access Denied!" + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<?> handleAddressNotFoundException(AddressNotFoundException ex){
        return new ResponseEntity<>("Address Not Found" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(SessionExpiredException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleSessionExpiredException(SessionExpiredException ex){
        return new ResponseEntity<>("Please Login Again" + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

}
