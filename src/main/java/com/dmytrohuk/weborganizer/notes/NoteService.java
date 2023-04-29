package com.dmytrohuk.weborganizer.notes;

import com.dmytrohuk.weborganizer.users.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteMapper noteMapper;

    public Note createNote(NoteCreateDTO noteDTO) {
        Note note = noteMapper.toNote(noteDTO);
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public NoteCreateDTO viewNote(Long id) {
        Note existingNote = noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id " + id + " does not exist")
                )
        );
        return noteMapper.toNoteCreateDTO(existingNote);
    }

    @Transactional
    public Note updateNote(Long id, NoteDTO noteDTO) {
        Note existingNote =noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id " + id + " does not exist")
                )
        );
        noteMapper.updateNote(noteDTO, existingNote);
        return noteRepository.save(existingNote);
    }

    public void deleteUser(Long id) {
        Note existingNote = noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id " + id + " does not exist")
                )
        );
        noteRepository.deleteById(id);
    }
}
