package com.dmytrohuk.weborganizer.calendars;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CalendarsMapper {
    CalendarsViewDTO toViewDTO(Calendars calendar);
    Calendars toCalendar(CalendarsCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    void updateContact(CalendarsUpdateDTO updateDTO, @MappingTarget Calendars calendar);
}
