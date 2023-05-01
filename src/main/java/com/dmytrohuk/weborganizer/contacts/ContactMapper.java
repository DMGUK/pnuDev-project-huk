package com.dmytrohuk.weborganizer.contacts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactUpdateDTO toContactDTO(Contact contact);

    ContactViewDTO toViewDTO(Contact contact);

    ContactCreateDTO toContactCreateDTO(Contact contact);

    Contact toContact(ContactCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    void updateContact(ContactUpdateDTO contactDTO, @MappingTarget Contact contact);
}
