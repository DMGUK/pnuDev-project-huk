package com.dmytrohuk.weborganizer.users;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(Throwable err){
        super(err);
    }
}
