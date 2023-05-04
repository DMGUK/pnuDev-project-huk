package com.dmytrohuk.weborganizer.reminders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReminderMapper {
    ReminderViewDTO toViewDTO(Reminder reminder);

    List<ReminderViewDTO> toViewDTOCalendarId(List<Reminder> reminder);

    Reminder toReminder(ReminderCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    void updateReminder(ReminderUpdateDTO updateDTO, @MappingTarget Reminder reminder);

}
