package com.dmytrohuk.weborganizer.notes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteUpdateDTO toNoteDTO(Note note);

    NoteViewDTO toViewDTO(Note note);

    NoteCreateDTO toNoteCreateDTO(Note note);

    Note toNote(NoteCreateDTO noteCreateDTO);

    @Mapping(target = "id", ignore = true)
    void updateNote(NoteUpdateDTO noteUpdateDTO, @MappingTarget Note note);
}
