package com.example.springbootCRUD_h2db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle RuntimeException (like User not found)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    //  // 400 → Validation
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) { ... }

    // // 404 → Domain / resource not found
    // @ExceptionHandler(UserNotFoundException.class)
    // public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) { ... }

    // // 500 → Unknown / unexpected errors
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Map<String, String>> handleAll(Exception ex) {
    //     ex.printStackTrace(); // important for debugging
    //     Map<String,String> error = new HashMap<>();
    //     error.put("error","Internal Server Error");
    //     return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
