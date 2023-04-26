package com.dmytrohuk.weborganizer.notes;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(Throwable err) {
        super(err);
    }
}
