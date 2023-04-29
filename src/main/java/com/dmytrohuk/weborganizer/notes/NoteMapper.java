package com.dmytrohuk.weborganizer.notes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteDTO toNoteDTO(Note note);

    NoteCreateDTO toNoteCreateDTO(Note note);

    Note toNote(NoteCreateDTO noteCreateDTO);

    @Mapping(target = "id", ignore = true)
    void updateNote(NoteDTO noteDTO, @MappingTarget Note note);
}
