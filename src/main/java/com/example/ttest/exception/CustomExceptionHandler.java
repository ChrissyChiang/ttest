//package com.example.ttest.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class CustomExceptionHandler {
//    @ExceptionHandler(ApiException.class)
//    public ResponseEntity<ErrorResult> handleApiException(ApiException e) {
//        ErrorResult errorResult = new ErrorResult(e.getMessage());
//        return new ResponseEntity<>(errorResult, HttpStatus.UNAUTHORIZED);
//    }
//}
