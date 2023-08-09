package com.check_in_system.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.check_in_system.demo.entities.Tables.ErrorMessage;

@ControllerAdvice
public class NotFoundExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handler(NotFoundException exp, WebRequest request) {

        ErrorMessage messsage = new ErrorMessage(HttpStatus.NOT_FOUND, exp.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messsage);
    }



}
