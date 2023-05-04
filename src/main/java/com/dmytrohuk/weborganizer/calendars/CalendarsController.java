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
public class CalendarsController {
    private final CalendarsService calendarsService;

    @PostMapping
    public void createNewNote(@RequestBody CalendarsCreateDTO createDTO){
        calendarsService.createCalendar(createDTO);
    }

    @PutMapping(path = "{id}")
    public void updateContact(@PathVariable("id") Long calendarId, @RequestBody CalendarsUpdateDTO updateDTO){
        calendarsService.editCalendar(calendarId, updateDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteContact(@PathVariable("id") Long calendarId){
        calendarsService.deleteCalendar(calendarId);
    }
}
