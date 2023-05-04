package com.dmytrohuk.weborganizer.notes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteViewDTO toViewDTO(Note note);

    Note toNote(NoteCreateDTO noteCreateDTO);

    @Mapping(target = "id", ignore = true)
    void updateNote(NoteUpdateDTO noteUpdateDTO, @MappingTarget Note note);
}
