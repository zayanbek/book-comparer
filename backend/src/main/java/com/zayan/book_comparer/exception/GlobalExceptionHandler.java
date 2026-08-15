package com.zayan.book_comparer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(BookNotFoundException.class)
     public ResponseEntity<Map<String, String>> handleBookNotFound(BookNotFoundException ex) {

          return ResponseEntity
                  .status(HttpStatus.BAD_REQUEST)
                  .body(Map.of(
                          "error", "Bad Request",
                          "message", ex.getMessage()
                  ));
     }

     @ExceptionHandler(BookTitleNotSpecificException.class)
     public ResponseEntity<Map<String, String>> handleBookTitleNotSpecific(BookTitleNotSpecificException ex) {

          return ResponseEntity
                  .status(HttpStatus.BAD_REQUEST)
                  .body(Map.of(
                          "error", "Bad Request",
                          "message", ex.getMessage()
                  ));

     }
}