package com.dmytrohuk.weborganizer.notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteUpdateDTO {
    private String title;

    private String content;

    private final LocalDate updated_date = LocalDate.now();

    private Long userId;
}