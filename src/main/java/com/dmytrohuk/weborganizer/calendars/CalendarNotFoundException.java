package com.dmytrohuk.weborganizer.calendars;

public class CalendarNotFoundException extends RuntimeException{
    public CalendarNotFoundException(String msg) {
        super(msg);
    }
}
