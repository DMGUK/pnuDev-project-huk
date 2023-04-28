package com.dmytrohuk.weborganizer.users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);

    UserCreateDTO toUserCreateDTO(User user);

    User toUser(UserCreateDTO userDTO);

    @Mapping(target = "id", ignore = true)
    void updateUser(UserDTO userDTO, @MappingTarget User user);
}
