package com.dmytrohuk.weborganizer.notes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;

    public NoteViewDTO createNote(NoteCreateDTO noteDTO) {
        Note note = noteMapper.toNote(noteDTO);
        return noteMapper.toViewDTO(noteRepository.save(note));
    }

    public NoteViewDTO viewNote(Long id) {
        Note existingNote = noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id " + id + " does not exist")
                )
        );
        return noteMapper.toViewDTO(existingNote);
    }

    @Transactional
    public NoteViewDTO updateNote(Long id, NoteUpdateDTO updateDTO) {
        Note existingNote =noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id " + id + " does not exist")
                )
        );
        noteMapper.updateNote(updateDTO, existingNote);
        existingNote.setUpdatedDate(LocalDate.now());
        return noteMapper.toViewDTO(noteRepository.save(existingNote));
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
