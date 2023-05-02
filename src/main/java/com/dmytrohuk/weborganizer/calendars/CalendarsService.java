package com.dmytrohuk.weborganizer.calendars;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CalendarsService {
    private final CalendarsRepository calendarsRepository;

    private final CalendarsMapper calendarsMapper;

    public Calendars createCalendar(CalendarsCreateDTO createDTO){
        Calendars calendar = calendarsMapper.toCalendar(createDTO);
        return calendarsRepository.save(calendar);
    }

    @Transactional
    public CalendarsCreateDTO editCalendar(Long id, CalendarsUpdateDTO updateDTO){
        Calendars existingCalendar = calendarsRepository.findById(id).orElseThrow(
                () -> new CalendarsNotFoundException(
                        new Throwable("Calendar with id " + id + " does not exist")
                )
        );
        calendarsMapper.updateContact(updateDTO, existingCalendar);
        return calendarsMapper.toCalendarCreateDTO(calendarsRepository.save(existingCalendar));
    }

    public void deleteCalendar(Long id){
        Calendars existingCalendar = calendarsRepository.findById(id).orElseThrow(
                () -> new CalendarsNotFoundException(
                        new Throwable("Calendar with id " + id + " does not exist")
                )
        );
        calendarsRepository.deleteById(id);
    }
}
