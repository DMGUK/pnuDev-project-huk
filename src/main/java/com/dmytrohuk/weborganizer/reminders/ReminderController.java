package com.dmytrohuk.weborganizer.reminders;

import com.dmytrohuk.weborganizer.security.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@AllArgsConstructor
public class ReminderController {
    private final ReminderService reminderService;

    @PostMapping
    public void createReminder(@RequestBody ReminderCreateDTO createDTO, @AuthenticationPrincipal AuthUser authUser){
        reminderService.createReminder(createDTO, authUser);
    }

    @GetMapping(path = "/", name = "calendarId")
    public List<ReminderViewDTO> viewReminderByCalendarId(@Name("calendarId") Long calendarId){
        return reminderService.getRemindersByCalendarId(calendarId);
    }

    @GetMapping(path = "{id}")
    public ReminderViewDTO viewReminderByReminderId(@PathVariable("id") Long reminderId){
        return reminderService.viewReminderByReminderId(reminderId);
    }

    @PutMapping(path = "{id}")
    public void updateReminder(@PathVariable("id") Long calendarId, @RequestBody ReminderUpdateDTO updateDTO){
        reminderService.updateReminder(calendarId, updateDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteReminder(@PathVariable("id") Long calendarId){
        reminderService.deleteReminder(calendarId);
    }
}
