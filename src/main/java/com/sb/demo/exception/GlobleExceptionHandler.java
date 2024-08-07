package com.sb.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(value = UserAlreadyExsistsException.class)
    public ResponseEntity<UserException> handleAlredyExistexcep(UserAlreadyExsistsException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        UserException.builder()
                                .status(HttpStatus.NOT_FOUND)
                                .message(e.getMessage())
                                .build()
                );

    }

    @ExceptionHandler(value = UserNotExsistsException.class)
    public ResponseEntity<UserException> handleNotexistexcep(UserNotExsistsException e){

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(
                        UserException.builder()
                                .status(HttpStatus.FOUND)
                                .message(e.getMessage())
                                .build()
                );
    }


}
