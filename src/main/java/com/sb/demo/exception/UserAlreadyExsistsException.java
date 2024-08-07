package com.sb.demo.exception;

public class UserAlreadyExsistsException extends RuntimeException{

    public UserAlreadyExsistsException(){}

    public UserAlreadyExsistsException(String message){
        super(message);
    }

}
