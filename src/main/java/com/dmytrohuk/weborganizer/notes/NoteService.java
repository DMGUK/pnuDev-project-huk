package com.dmytrohuk.weborganizer.notes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;

    public Note createNote(NoteDTO noteDTO) {
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setCreated_date(noteDTO.getCreatedDate());
        note.setUpdated_date(noteDTO.getUpdatedDate());
        note.setUserId(noteDTO.getUser().getId());

        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> viewNote(Long id) {
        Note existingNote =noteRepository.findById(id).orElseThrow(
            () -> new NoteNotFoundException(
                    new Throwable("Note with id " + id + " does not exist")
            )
        );
        return noteRepository.findById(id);
    }

    @Transactional
    public Note updateNote(Long id, NoteDTO noteDTO) {
        Note existingNote =noteRepository.findById(id).orElseThrow(
            () -> new NoteNotFoundException(
                new Throwable("Note with id " + id + " does not exist")
            )
        );
        existingNote.setTitle(noteDTO.getTitle());
        existingNote.setContent(noteDTO.getContent());
        existingNote.setUpdated_date(LocalDate.now());
        return noteRepository.save(existingNote);
    }

    public void deleteNote(Long id) {
        Note existingNote = noteRepository.findById(id).orElseThrow(
            () -> new NoteNotFoundException(
                new Throwable("Note with id " + id + " does not exist")
            )
        );
        noteRepository.deleteById(id);
    }
}
