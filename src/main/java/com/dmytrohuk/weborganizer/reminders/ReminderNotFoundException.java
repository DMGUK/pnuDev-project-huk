package com.dmytrohuk.weborganizer.reminders;

public class ReminderNotFoundException extends RuntimeException{
    public ReminderNotFoundException(String msg){
        super(msg);
    }
}
