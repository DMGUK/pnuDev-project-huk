package com.dmytrohuk.weborganizer.reminders;

import com.dmytrohuk.weborganizer.calendars.Calendars;
import com.dmytrohuk.weborganizer.calendars.CalendarsCreateDTO;
import com.dmytrohuk.weborganizer.calendars.CalendarsUpdateDTO;
import com.dmytrohuk.weborganizer.notes.NoteCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    private final ReminderService reminderService;
    @Autowired
    public ReminderController(ReminderService reminderService){
        this.reminderService = reminderService;
    }

    @PostMapping
    public void createReminder(@RequestBody ReminderCreateDTO createDTO){
        reminderService.createReminder(createDTO);
    }

    @GetMapping(path = "/", name = "calendarId")
    public Optional<List<Reminder>> viewReminderByCalendarId(@Name("calendarId") Long calendarId){
        return reminderService.getRemindersByCalendarId(calendarId);
    }

    @GetMapping(path = "{reminder-id}")
    public ReminderViewDTO viewReminderByReminderId(@PathVariable("reminder-id") Long reminderId){
        return reminderService.viewReminderByReminderId(reminderId);
    }

    @PutMapping(path = "{reminder-id}")
    public void updateReminder(@PathVariable("reminder-id") Long calendarId, @RequestBody ReminderUpdateDTO updateDTO){
        reminderService.updateReminder(calendarId, updateDTO);
    }

    @DeleteMapping(path = "{reminder-id}")
    public void deleteReminder(@PathVariable("reminder-id") Long calendarId){
        reminderService.deleteReminder(calendarId);
    }
}
