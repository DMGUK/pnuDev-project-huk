package com.dmytrohuk.weborganizer.notes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteCreateDTO {
    private String title;

    private String content;

    private LocalDate createdDate;

    private LocalDate updatedDate;
}
