package org.example.baitapthuctap.exception;

import org.example.baitapthuctap.model.response.ResponseObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseObject<?> handleRuntime(RuntimeException ex){
        return ResponseObject.error(ex.getMessage());
    }
}
