package com.dmytrohuk.weborganizer.reminders;

import com.dmytrohuk.weborganizer.calendars.Calendars;
import com.dmytrohuk.weborganizer.calendars.CalendarsCreateDTO;
import com.dmytrohuk.weborganizer.calendars.CalendarsUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ReminderMapper {
    ReminderUpdateDTO toReminderDTO(Reminder reminder);

    ReminderViewDTO toViewDTO(Reminder reminder);

    List<ReminderViewDTO> toViewDTOCalendarId(List<Reminder> reminder);

    ReminderCreateDTO toReminderCreateDTO(Reminder reminder);

    Reminder toReminder(ReminderCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    void updateReminder(ReminderUpdateDTO updateDTO, @MappingTarget Reminder reminder);

}
