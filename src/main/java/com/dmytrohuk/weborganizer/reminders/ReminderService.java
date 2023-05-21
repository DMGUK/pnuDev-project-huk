package com.dmytrohuk.weborganizer.reminders;

import com.dmytrohuk.weborganizer.security.AuthUser;
import com.dmytrohuk.weborganizer.security.AuthUserService;
import com.dmytrohuk.weborganizer.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;

    private final ReminderMapper reminderMapper;

    private final UserRepository userRepository;

    private final AuthUserService authUserService;

    public ReminderViewDTO createReminder(ReminderCreateDTO createDTO, AuthUser authUser){
        Reminder reminder = reminderMapper.toReminder(createDTO);
        UserDetails user = authUserService.loadUserByUsername(authUser.getUsername());
        Long userId = userRepository.findByUsername(user.getUsername()).getId();
        reminder.setUserId(userId);
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
