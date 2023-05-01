package com.dmytrohuk.weborganizer.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getUsers(){
        return noteService.getAllNotes();
    }

    @GetMapping(path = "{note-id}")
    public NoteViewDTO getUserById(@PathVariable("note-id") Long noteId){
        return noteService.viewNote(noteId);
    }

    @PostMapping
    public void createNewNote(@RequestBody NoteCreateDTO noteDTO){
        noteService.createNote(noteDTO);
    }


    @DeleteMapping(path="{note-id}")
    public void deleteUser(@PathVariable("note-id") Long userId){
        noteService.deleteUser(userId);
    }


    // create a RequestBody to the User
        /*
    @RequestBody UpdateUser updateUser
    *
    */
    @PutMapping(path = "{note-id}")
    public void updateNote(@PathVariable("note-id") Long userId,@RequestBody NoteUpdateDTO noteUpdateDTO){
        noteService.updateNote(userId, noteUpdateDTO);
    }
}
