package com.dmytrohuk.weborganizer.notes;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteUpdate {
    private String title;

    private String content;
}
