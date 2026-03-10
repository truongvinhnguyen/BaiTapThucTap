package org.example.baitapthuctap.exception;

import org.example.baitapthuctap.model.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseObject<?> handleCustomException(CustomException ex){
        return new ResponseObject<>(false, null, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseObject<?> handleException(Exception ex){
        return new ResponseObject<>(false, null, "Internal Server Error");
    }
}
