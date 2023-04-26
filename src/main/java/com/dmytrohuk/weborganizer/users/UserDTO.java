package com.dmytrohuk.weborganizer.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    /* TODO :
     *  Create separate UpdateUserDTO class
     *  Add a list of notes into UpdateUserDTO
     *  This UserDTO will map into User using MapStruct interface
     * */

    private String username;

    private String password;

    private String firstName;

    private String surname;

    private String email;

    private String address;
}