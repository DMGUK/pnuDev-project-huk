package com.dmytrohuk.weborganizer.calendars;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendars")
@AllArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    public void createNewNote(@RequestBody CalendarCreateDTO createDTO){
        calendarService.createCalendar(createDTO);
    }

    @PutMapping(path = "{id}")
    public void updateContact(@PathVariable("id") Long calendarId, @RequestBody CalendarUpdateDTO updateDTO){
        calendarService.editCalendar(calendarId, updateDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteContact(@PathVariable("id") Long calendarId){
        calendarService.deleteCalendar(calendarId);
    }
}
