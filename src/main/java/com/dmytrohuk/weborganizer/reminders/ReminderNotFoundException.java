package com.dmytrohuk.weborganizer.reminders;

public class ReminderNotFoundException extends RuntimeException{
    public ReminderNotFoundException(Throwable err){
        super(err);
    }
}
