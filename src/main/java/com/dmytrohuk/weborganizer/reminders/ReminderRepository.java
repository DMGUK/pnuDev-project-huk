package com.dmytrohuk.weborganizer.reminders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByCalendarId(Long id);
    Reminder findByTitle(String title);
    /*
    * TODO:
    *  Create a findByCalendarId method
    *  It'll be used in ReminderService
    *  Delete password attribute from UserViewDTO class
    *  Rename UserViewDTO to UserViewDTO & create UserUpdateDTO
    *  The same can applied to other entities
    * */
}
