package com.dmytrohuk.weborganizer.reminders;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;

    private final ReminderMapper reminderMapper;

    public Reminder createReminder(ReminderCreateDTO createDTO){
        Reminder reminder = reminderMapper.toReminder(createDTO);
        return reminderRepository.save(reminder);
    }

    public ReminderViewDTO viewReminderByReminderId(Long id) {
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
            () -> new ReminderNotFoundException(
                    new Throwable("Reminder with id " + id + " does not exist")
            )
        );
        return reminderMapper.toViewDTO(existingReminder);
    }

    public List<Reminder> getRemindersByCalendarId(Long calendarId) {
        List<Reminder> reminders = reminderRepository.findByCalendarId(calendarId);
        if (reminders.isEmpty()){
            throw new ReminderNotFoundException(new Throwable("Calendar with id " + calendarId + " does not exist"));
        }
        return reminders;
    }


    @Transactional
    public ReminderCreateDTO updateReminder(Long id, ReminderUpdateDTO updateDTO){
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
            () -> new ReminderNotFoundException(
                    new Throwable("Reminder with id " + id + " does not exist")
            )
        );
        reminderMapper.updateReminder(updateDTO, existingReminder);
        return reminderMapper.toReminderCreateDTO(reminderRepository.save(existingReminder));
    }

    public void deleteReminder(Long id){
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
                () -> new ReminderNotFoundException(
                        new Throwable("Reminder with id " + id + " does not exist")
                )
        );
        reminderRepository.deleteById(id);
    }
}
