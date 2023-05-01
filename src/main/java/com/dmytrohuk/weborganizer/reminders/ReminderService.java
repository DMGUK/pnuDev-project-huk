package com.dmytrohuk.weborganizer.reminders;

import com.dmytrohuk.weborganizer.calendars.Calendars;
import com.dmytrohuk.weborganizer.calendars.CalendarsNotFoundException;
import com.dmytrohuk.weborganizer.calendars.CalendarsRepository;
import com.dmytrohuk.weborganizer.notes.Note;
import com.dmytrohuk.weborganizer.notes.NoteCreateDTO;
import com.dmytrohuk.weborganizer.notes.NoteNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReminderService {
    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private CalendarsRepository calendarsRepository;

    @Autowired
    private ReminderMapper reminderMapper;

    public Reminder createReminder(ReminderCreateDTO createDTO){
        Reminder reminder = reminderMapper.toReminder(createDTO);
        return reminderRepository.save(reminder);
    }

    public ReminderViewDTO viewReminderByReminderId(Long id) {
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
            () -> new ReminderNotFoundException(
                    new Throwable("Note with id " + id + " does not exist")
            )
        );
        return reminderMapper.toViewDTO(existingReminder);
    }

    public List<ReminderViewDTO> viewReminderByCalendarId(Long calendarId) {
        List<Reminder> existingReminders = reminderRepository.findByCalendarId(calendarId);
        return reminderMapper.toViewDTOCalendarId(existingReminders);
    }

    @Transactional
    public ReminderCreateDTO updateReminder(Long id, ReminderUpdateDTO updateDTO){
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
            () -> new ReminderNotFoundException(
                    new Throwable("Note with id " + id + " does not exist")
            )
        );
        reminderMapper.updateReminder(updateDTO, existingReminder);
        return reminderMapper.toReminderCreateDTO(reminderRepository.save(existingReminder));
    }

    public void deleteReminder(Long id){
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
                () -> new ReminderNotFoundException(
                        new Throwable("Note with id " + id + " does not exist")
                )
        );
        reminderRepository.deleteById(id);
    }
}
