package com.dmytrohuk.weborganizer.users;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String msg){
        super(msg);
    }
}
