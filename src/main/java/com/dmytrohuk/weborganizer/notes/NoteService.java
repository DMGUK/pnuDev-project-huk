package com.dmytrohuk.weborganizer.notes;

import com.dmytrohuk.weborganizer.config.AuthUser;
import com.dmytrohuk.weborganizer.config.AuthUserService;
import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    private final AuthUserService authUserService;

    private final UserRepository userRepository;

    private final NoteMapper noteMapper;

    public NoteViewDTO createNote(NoteCreateDTO noteDTO, AuthUser authUser) {
        Note note = noteMapper.toNote(noteDTO);
        UserDetails user = authUserService.loadUserByUsername(authUser.getUsername());
        Long userId = userRepository.findByUsername(user.getUsername()).getId();
        note.setUserId(userId);
        return noteMapper.toViewDTO(noteRepository.save(note));
    }

    public NoteViewDTO viewNote(Long id) {
        Note existingNote = noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id %d does not exist".formatted(id))
                )
        );
        return noteMapper.toViewDTO(existingNote);
    }

    @Transactional
    public NoteViewDTO updateNote(Long id, NoteUpdateDTO updateDTO) {
        Note existingNote =noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id %d does not exist".formatted(id))
                )
        );
        noteMapper.updateNote(updateDTO, existingNote);
        existingNote.setUpdatedDate(LocalDate.now());
        return noteMapper.toViewDTO(noteRepository.save(existingNote));
    }

    public void deleteUser(Long id) {
        Note existingNote = noteRepository.findById(id).orElseThrow(
                () -> new NoteNotFoundException(
                        new Throwable("Note with id %d does not exist".formatted(id))
                )
        );
        noteRepository.deleteById(id);
    }
}
