package com.dmytrohuk.weborganizer.reminders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderCreateDTO {
    private String title;

    private String content;

    private LocalDate dueDate;

    private Long calendarId;
}
