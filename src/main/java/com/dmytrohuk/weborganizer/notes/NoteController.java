package com.dmytrohuk.weborganizer.notes;

import com.dmytrohuk.weborganizer.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping(path = "{note-id}")
    public Optional<Note> getNoteById(@PathVariable("note-id") Long noteId){
        return noteService.viewNote(noteId);
    }

    @PostMapping
    public void createNewNote(@RequestBody NoteDTO noteDTO){
        noteService.createNote(noteDTO);
    }

    @DeleteMapping(path="{note-id}")
    public void deleteNote(@PathVariable("note-id") Long userId){
        noteService.deleteNote(userId);
    }

    @PutMapping(path = "{note-id}")
    public void updateNote(@PathVariable("note-id") Long userId,@RequestBody NoteDTO noteDTO){
        noteService.updateNote(userId, noteDTO);
    }
}
