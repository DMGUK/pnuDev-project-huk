package com.dmytrohuk.weborganizer.calendars;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    private final CalendarMapper calendarMapper;

    public CalendarViewDTO createCalendar(CalendarCreateDTO createDTO){
        Calendar calendar = calendarMapper.toCalendar(createDTO);
        return calendarMapper.toViewDTO(calendarRepository.save(calendar));
    }

    @Transactional
    public CalendarViewDTO editCalendar(Long id, CalendarUpdateDTO updateDTO){
        Calendar existingCalendar = calendarRepository.findById(id).orElseThrow(
                () -> new CalendarNotFoundException(
                        new Throwable("Calendar with id " + id + " does not exist")
                )
        );
        calendarMapper.updateContact(updateDTO, existingCalendar);
        return calendarMapper.toViewDTO(calendarRepository.save(existingCalendar));
    }

    public void deleteCalendar(Long id){
        Calendar existingCalendar = calendarRepository.findById(id).orElseThrow(
                () -> new CalendarNotFoundException(
                        new Throwable("Calendar with id " + id + " does not exist")
                )
        );
        calendarRepository.deleteById(id);
    }
}
