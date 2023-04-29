package com.dmytrohuk.weborganizer.contacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUpdateDTO {
    private String name;

    private String phone;

    private String surname;
}