package com.app.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalHandlerException {

    private static final Logger LOGGER = Logger.getLogger(GlobalHandlerException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>ManagerException(Exception e, WebRequest r){
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>("Error" + e.getMessage(),HttpStatus.BAD_REQUEST);

    }





}
