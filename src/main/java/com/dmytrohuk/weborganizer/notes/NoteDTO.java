package com.dmytrohuk.weborganizer.notes;

import com.dmytrohuk.weborganizer.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
    private String title;

    private String content;

    private final LocalDate updated_date = LocalDate.now();

    private Long userId;
}