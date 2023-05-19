package com.dmytrohuk.weborganizer;

import com.dmytrohuk.weborganizer.notes.Note;
import com.dmytrohuk.weborganizer.notes.NoteRepository;
import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class NoteRepositoryTest {
    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("testTitle");
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        Optional<Note> result = noteRepository.findById(1L);
        assertEquals(Optional.of(note), result);
    }

    @Test
    public void userRepositoryUsernames(){
        String title = "testTitle";
        String content = "testContent";
        Note testNote = new Note();
        testNote.setId(4L);
        testNote.setTitle(title);
        testNote.setContent(content);
        when(noteRepository.findByTitle(title)).thenReturn(testNote);
        Note resultNote = noteRepository.findByTitle(title);
        assertEquals(testNote, resultNote);
    }
}
