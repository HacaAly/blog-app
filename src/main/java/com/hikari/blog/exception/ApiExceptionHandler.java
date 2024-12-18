package com.hikari.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handler(ApiException exception) {
        List<String> errorMessage = new ArrayList<>(Collections.singletonList(exception.getMessage()));
        ApiExceptionResponse response = ApiExceptionResponse.builder().ErrorMessage(errorMessage).build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiExceptionResponse> handler(SQLIntegrityConstraintViolationException exception) {
        List<String> errorMessage = new ArrayList<>(Collections.singletonList(exception.getMessage()));
        ApiExceptionResponse response = ApiExceptionResponse.builder().ErrorMessage(errorMessage).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handler(MethodArgumentNotValidException exception) {
        List<String> errorMessage = new ArrayList<>();
        exception.getFieldErrors().forEach(fieldError -> errorMessage.add(fieldError.getDefaultMessage()));
        ApiExceptionResponse response = ApiExceptionResponse.builder().ErrorMessage(errorMessage).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }
}
