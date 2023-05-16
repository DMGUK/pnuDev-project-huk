package com.dmytrohuk.weborganizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;


import com.dmytrohuk.weborganizer.notes.Note;
import com.dmytrohuk.weborganizer.notes.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NoteRepositoryTest {
    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById(){
        Note note = new Note();
        note.setId(1L);
        note.setTitle("title");
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        Optional<Note> result = noteRepository.findById(1L);
        assertEquals(Optional.of(note), result);
    }

    @Test
    public void testNotesTitles(){
        String title = "testuser";
        String description = "testpassword";
        Note testNote = new Note();
        testNote.setId(4L);
        testNote.setTitle(title);
        testNote.setContent(description);
        when(noteRepository.findByTitle(title)).thenReturn(testNote);
        Note resultNote = noteRepository.findByTitle(testNote.getTitle());
        assertEquals(testNote, resultNote);
    }
}
