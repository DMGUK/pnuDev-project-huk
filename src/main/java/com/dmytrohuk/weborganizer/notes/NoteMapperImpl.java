package com.dmytrohuk.weborganizer.notes;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteDTO toNoteDTO(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteDTO.NoteDTOBuilder noteDTO = NoteDTO.builder();

        noteDTO.id( note.getId() );
        noteDTO.title( note.getTitle() );
        noteDTO.content( note.getContent() );
        noteDTO.user( note.getUser() );

        return noteDTO.build();
    }

    @Override
    public NoteCreateDTO toNoteCreateDTO(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteCreateDTO noteCreateDTO = new NoteCreateDTO();

        noteCreateDTO.setTitle( note.getTitle() );
        noteCreateDTO.setContent( note.getContent() );
        noteCreateDTO.setCreated_date( note.getCreated_date() );
        noteCreateDTO.setUpdated_date( note.getUpdated_date() );
        noteCreateDTO.setUserId( note.getUserId() );

        return noteCreateDTO;
    }

    @Override
    public Note toNote(NoteCreateDTO noteDTO) {
        if ( noteDTO == null ) {
            return null;
        }

        Note note = new Note();

        note.setTitle( noteDTO.getTitle() );
        note.setContent( noteDTO.getContent() );
        note.setCreated_date( noteDTO.getCreated_date() );
        note.setUpdated_date( noteDTO.getUpdated_date() );
        note.setUserId( noteDTO.getUserId() );

        return note;
    }

    @Override
    public void updateNote(NoteDTO noteDTO, Note note) {
        if ( noteDTO == null ) {
            return;
        }

        note.setUserId( noteDTO.getUser().getId());
        note.setTitle( noteDTO.getTitle() );
        note.setContent( noteDTO.getContent() );
        note.setUpdated_date(LocalDate.now());
    }
}
