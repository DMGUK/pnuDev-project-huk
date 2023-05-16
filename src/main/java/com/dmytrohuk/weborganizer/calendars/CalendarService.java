package com.dmytrohuk.weborganizer.calendars;

import com.dmytrohuk.weborganizer.security.AuthUser;
import com.dmytrohuk.weborganizer.security.AuthUserService;
import com.dmytrohuk.weborganizer.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    private final CalendarMapper calendarMapper;

    private final UserRepository userRepository;

    private final AuthUserService authUserService;

    public CalendarViewDTO createCalendar(CalendarCreateDTO createDTO, AuthUser authUser){
        Calendar calendar = calendarMapper.toCalendar(createDTO);
        UserDetails user = authUserService.loadUserByUsername(authUser.getUsername());
        Long userId = userRepository.findByUsername(user.getUsername()).getId();
        calendar.setUserId(userId);
        return calendarMapper.toViewDTO(calendarRepository.save(calendar));
    }

    @Transactional
    public CalendarViewDTO editCalendar(Long id, CalendarUpdateDTO updateDTO){
        Calendar existingCalendar = calendarRepository.findById(id).orElseThrow(
                () -> new CalendarNotFoundException("Calendar with id %d does not exist".formatted(id))
        );
        calendarMapper.updateContact(updateDTO, existingCalendar);
        return calendarMapper.toViewDTO(calendarRepository.save(existingCalendar));
    }

    public void deleteCalendar(Long id){
        Calendar existingCalendar = calendarRepository.findById(id).orElseThrow(
                () -> new CalendarNotFoundException("Calendar with id %d does not exist".formatted(id))
        );
        calendarRepository.deleteById(id);
    }
}
