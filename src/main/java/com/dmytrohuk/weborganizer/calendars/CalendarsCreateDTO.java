package com.dmytrohuk.weborganizer.calendars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarsCreateDTO {
    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String timezone;
}
