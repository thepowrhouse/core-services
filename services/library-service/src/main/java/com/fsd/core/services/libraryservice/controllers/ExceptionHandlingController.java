package com.fsd.core.services.libraryservice.controllers;

import com.fsd.core.services.libraryservice.exception.ExceptionResponse;
import com.fsd.core.services.libraryservice.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        //log message
        ex.printStackTrace();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        response.setErrorMessage("Resource Not Found: " + ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(Exception ex) {
        //log message
        ex.printStackTrace();
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        response.setErrorMessage("Unable to process the request:" + ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
}
