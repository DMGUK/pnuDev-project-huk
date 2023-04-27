package com.dmytrohuk.weborganizer.users;

import org.mapstruct.Mapper;


@Mapper
public interface UserMapper {
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}