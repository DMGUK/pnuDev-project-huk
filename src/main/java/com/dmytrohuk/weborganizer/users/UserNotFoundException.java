package com.dmytrohuk.weborganizer.users;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Throwable err){
        super(err);
    }
}
