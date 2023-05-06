package com.dmytrohuk.weborganizer.calendars;

public class CalendarNotFoundException extends RuntimeException{
    public CalendarNotFoundException(Throwable err) {
        super(err);
    }
}
