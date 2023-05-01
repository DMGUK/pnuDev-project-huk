package com.dmytrohuk.weborganizer.notes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteViewDTO {
    private String title;

    private String content;

    private LocalDate created_date;

    private LocalDate updated_date;

    private Long userId;
}
