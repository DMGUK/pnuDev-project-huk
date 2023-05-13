package com.dmytrohuk.weborganizer.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String username;

    private String password;

    private String firstName;

    private String surname;

    private String email;

    private String address;
}
