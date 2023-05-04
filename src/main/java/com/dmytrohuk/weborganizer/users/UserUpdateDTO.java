package com.dmytrohuk.weborganizer.users;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private String username;

    private String password;

    private String firstName;

    private String surname;

    private String email;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
