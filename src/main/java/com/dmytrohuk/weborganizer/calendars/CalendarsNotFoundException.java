package com.dmytrohuk.weborganizer.calendars;

public class CalendarsNotFoundException extends RuntimeException{
    public CalendarsNotFoundException(Throwable err) {
        super(err);
    }
}
