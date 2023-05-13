package com.dmytrohuk.weborganizer.notes;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping(path = "{id}")
    public NoteViewDTO getUserById(@PathVariable("id") Long noteId){
        return noteService.viewNote(noteId);
    }

    @PostMapping
    public void createNewNote(@RequestBody NoteCreateDTO noteDTO, Authentication authentication){
        noteService.createNote(noteDTO, authentication);
    }


    @DeleteMapping(path="{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        noteService.deleteUser(userId);
    }


    @PutMapping(path = "{id}")
    public void updateNote(@PathVariable("id") Long userId,@RequestBody NoteUpdateDTO noteUpdateDTO){
        noteService.updateNote(userId, noteUpdateDTO);
    }
}
