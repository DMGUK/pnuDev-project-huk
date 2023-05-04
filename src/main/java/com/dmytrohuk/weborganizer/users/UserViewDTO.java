package com.dmytrohuk.weborganizer.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDTO {
    private String username;

    private String email;
}
