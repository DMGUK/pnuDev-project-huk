package com.dmytrohuk.weborganizer.notes;

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
    public List<Note> getUsers(){
        return noteService.getAllNotes();
    }

    @GetMapping(path = "{note-id}")
    public NoteCreateDTO getUserById(@PathVariable("note-id") Long noteId){
        return noteService.viewNote(noteId);
    }

    @PostMapping
    public Note createNewNote(@RequestBody NoteCreateDTO noteDTO){
        return noteService.createNote(noteDTO);
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
    public void updateNote(@PathVariable("note-id") Long userId,@RequestBody NoteDTO noteDTO){
        noteService.updateNote(userId, noteDTO);
    }
}
