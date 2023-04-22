package com.dmytrohuk.weborganizer.notes;

import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserService;
import com.dmytrohuk.weborganizer.users.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/notes")
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
    public Optional<Note> getUserById(@PathVariable("note-id") Long noteId){
        return noteService.viewNote(noteId);
    }

    @PostMapping
    public void createNewUser(@RequestBody Note note){
        noteService.createNote(note);
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
    public void updateNote(@PathVariable("note-id") Long userId,@RequestBody NoteUpdate noteUpdate){
        noteService.updateNote(userId, noteUpdate);
    }
}
