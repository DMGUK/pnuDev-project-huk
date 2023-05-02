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
    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private ReminderMapper reminderMapper;

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

    //problem with getRemindersByCalendarId(it doesn't reacts to orElseThrow())
    public List<Reminder> getRemindersByCalendarId(Long calendarId) {

        List<Reminder> reminders = reminderRepository.findByCalendarId(calendarId);
        return Optional.ofNullable(reminders)
                .orElseThrow(() -> new ReminderNotFoundException(new Throwable("No reminders found for calendar id: " + calendarId)));
//        return reminderRepository.findByCalendarId(calendarId);

//        public List<Note> getAllNotes() {
//        return noteRepository.findAll();
//    }
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
