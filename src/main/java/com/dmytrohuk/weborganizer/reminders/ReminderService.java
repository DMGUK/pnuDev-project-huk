package com.dmytrohuk.weborganizer.reminders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;

    private final ReminderMapper reminderMapper;

    public ReminderViewDTO createReminder(ReminderCreateDTO createDTO){
        Reminder reminder = reminderMapper.toReminder(createDTO);
        return reminderMapper.toViewDTO(reminderRepository.save(reminder));
    }

    public ReminderViewDTO viewReminderByReminderId(Long id) {
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
            () -> new ReminderNotFoundException("Reminder with id %d does not exist".formatted(id))
        );
        return reminderMapper.toViewDTO(existingReminder);
    }

    public List<ReminderViewDTO> getRemindersByCalendarId(Long calendarId) {
        List<Reminder> reminders = reminderRepository.findByCalendarId(calendarId);
        if (reminders.isEmpty()){
            throw new ReminderNotFoundException("Calendar with id %d does not exist".formatted(calendarId));
        }
        return reminderMapper.toViewDTOCalendarId(reminders);
    }


    @Transactional
    public ReminderViewDTO updateReminder(Long id, ReminderUpdateDTO updateDTO){
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
                () -> new ReminderNotFoundException("Reminder with id %d does not exist".formatted(id))
        );
        reminderMapper.updateReminder(updateDTO, existingReminder);
        return reminderMapper.toViewDTO(reminderRepository.save(existingReminder));
    }

    public void deleteReminder(Long id){
        Reminder existingReminder = reminderRepository.findById(id).orElseThrow(
                () -> new ReminderNotFoundException("Reminder with id %d does not exist".formatted(id))
        );
        reminderRepository.deleteById(id);
    }
}
