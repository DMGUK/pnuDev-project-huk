package com.dmytrohuk.weborganizer.calendars;

import com.dmytrohuk.weborganizer.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendars")
public class CalendarsController {
    private final CalendarsService calendarsService;

    @Autowired
    public CalendarsController(CalendarsService calendarsService) {
        this.calendarsService = calendarsService;
    }

    @PostMapping
    public void createNewNote(@RequestBody CalendarsCreateDTO createDTO){
        calendarsService.createCalendar(createDTO);
    }

    @PutMapping(path = "{calendar-id}")
    public void updateContact(@PathVariable("calendar-id") Long calendarId, @RequestBody CalendarsUpdateDTO updateDTO){
        calendarsService.editCalendar(calendarId, updateDTO);
    }

    @DeleteMapping(path = "{calendar-id}")
    public void deleteContact(@PathVariable("calendar-id") Long calendarId){
        calendarsService.deleteCalendar(calendarId);
    }
}
