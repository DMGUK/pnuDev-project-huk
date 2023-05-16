package com.dmytrohuk.weborganizer.contacts;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String msg) {
        super(msg);
    }
}
