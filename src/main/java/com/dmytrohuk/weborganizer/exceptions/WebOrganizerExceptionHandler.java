package com.dmytrohuk.weborganizer.exceptions;

import com.dmytrohuk.weborganizer.calendars.CalendarNotFoundException;
import com.dmytrohuk.weborganizer.contacts.Contact;
import com.dmytrohuk.weborganizer.contacts.ContactNotFoundException;
import com.dmytrohuk.weborganizer.notes.NoteNotFoundException;
import com.dmytrohuk.weborganizer.reminders.ReminderNotFoundException;
import com.dmytrohuk.weborganizer.users.UserAlreadyExistsException;
import com.dmytrohuk.weborganizer.users.UserNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class WebOrganizerExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String userAlreadyExistsException(UserAlreadyExistsException ex) {
        String message = ex.getMessage();
        return message;
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String userNotFoundException(UserNotFoundException ex) {
        String message = ex.getMessage();
        return message;
    }

    @ExceptionHandler(value = {NoteNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String noteNotFoundException(NoteNotFoundException ex) {
        String message = ex.getMessage();
        return message;
    }

    @ExceptionHandler(value = {ContactNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String contactNotFoundException(ContactNotFoundException ex) {
        String message = ex.getMessage();
        return message;
    }

    @ExceptionHandler(value = {CalendarNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String calendarNotFoundException(CalendarNotFoundException ex) {
        String message = ex.getMessage();
        return message;
    }

    @ExceptionHandler(value = {ReminderNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String reminderNotFoundException(ReminderNotFoundException ex) {
        String message = ex.getMessage();
        return message;
    }

}
