package com.dmytrohuk.weborganizer.contacts;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(Throwable err) {
        super(err);
    }
}
