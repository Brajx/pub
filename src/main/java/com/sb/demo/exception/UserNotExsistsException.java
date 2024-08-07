package com.sb.demo.exception;

public class UserNotExsistsException extends RuntimeException{

    public UserNotExsistsException(){}

    public UserNotExsistsException(String message){
        super(message);
    }

}
