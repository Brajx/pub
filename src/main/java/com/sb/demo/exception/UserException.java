package com.sb.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
