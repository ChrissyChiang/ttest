package com.example.ttest.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLServerException.class)
    public ResponseEntity<String> handleSQLServerException(SQLServerException ex) {
        if (ex.getMessage().contains("Violation of UNIQUE KEY constraint 'uniq_name'.")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name already exists!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}

