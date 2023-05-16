package com.dmytrohuk.weborganizer.calendars;

import com.dmytrohuk.weborganizer.security.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public void createNewNote(@RequestBody CalendarCreateDTO createDTO, @AuthenticationPrincipal AuthUser authUser){
        calendarService.createCalendar(createDTO, authUser);
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
