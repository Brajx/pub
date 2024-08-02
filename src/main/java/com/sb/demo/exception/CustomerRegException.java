package com.sb.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomerRegException  extends RuntimeException{
    private HttpStatus status;
    private String message;
}
