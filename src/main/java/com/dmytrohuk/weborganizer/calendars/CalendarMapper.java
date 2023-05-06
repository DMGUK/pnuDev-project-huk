package com.dmytrohuk.weborganizer.calendars;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    CalendarViewDTO toViewDTO(Calendar calendar);
    Calendar toCalendar(CalendarCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    void updateContact(CalendarUpdateDTO updateDTO, @MappingTarget Calendar calendar);
}
