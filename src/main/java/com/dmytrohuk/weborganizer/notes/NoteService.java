package com.dmytrohuk.weborganizer.notes;

import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> viewNote(Long id) { return noteRepository.findById(id); }

    @Transactional
    public Note updateNote(Long id, @RequestBody NoteUpdate noteUpdate) {
        Optional<Note> optionalNote = Optional.ofNullable(noteRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(
                        "student with id " + id + "does not exist"
                )
        ));
        Note existingNote = optionalNote.get();
        existingNote.setTitle(noteUpdate.getTitle());
        existingNote.setContent(noteUpdate.getContent());
        existingNote.setUpdated_date(LocalDate.now());
        return noteRepository.save(existingNote);
    }

    public void deleteUser(Long id) {
        Optional<Note> optionalNote = Optional.ofNullable(noteRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(
                        "student with id " + id + "does not exist"
                )
        ));
        noteRepository.deleteById(id);
    }
}
